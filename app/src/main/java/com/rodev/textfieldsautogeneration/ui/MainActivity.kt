package com.rodev.textfieldsautogeneration.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.rodev.textfieldsautogeneration.R
import com.rodev.textfieldsautogeneration.data.local.NotesDatabase
import com.rodev.textfieldsautogeneration.data.local.NotesRepositoryImpl
import com.rodev.textfieldsautogeneration.data.local.model.Notes
import com.rodev.textfieldsautogeneration.databinding.ActivityMainBinding
import com.rodev.textfieldsautogeneration.utils.AppPrefManager
import com.rodev.textfieldsautogeneration.utils.viewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prefs by lazy {
        AppPrefManager(this)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("PENUS", "ZAVARI_NOSOK")
        Log.e("PENUS", "KAKAWA")

        initVM()
        generateFields()
        setSavedValues()
    }

    private fun setSavedValues() {
        val notes = viewModel.getSavedNotes()
        binding.llContainter.children.forEach { textInputLayout ->
            (textInputLayout as? ViewGroup)?.children?.forEach { editText ->

                val currentView = (editText as? ViewGroup)?.children?.findLast { view ->
                    view is TextInputEditText
                }
                val savedText = notes.findLast {
                    it.viewId == currentView?.id
                }

                (currentView as? TextInputEditText)?.let {
                    with(currentView) {
                        setText(savedText?.info)
                        isActivated = true
                    }
                }
            }
        }
    }

    private fun initVM() {
        val notesRepository = NotesRepositoryImpl(
            NotesDatabase(this.applicationContext)
        )
        viewModel = ViewModelProvider(this, viewModelFactory {
            MainViewModel(notesRepository)
        })[MainViewModel::class.java]
    }

    private fun generateFields() {
        val fieldsNumber = prefs.getRandomFieldsNumber()
        val llMain = binding.llContainter

        val notes = viewModel.getSavedNotes()
        for (i in 1..fieldsNumber) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_texbox, null)
            val textInputLayout: TextInputLayout =
                view.findViewById(R.id.userIDTextInputLayout)
            val editText: TextInputEditText =
                view.findViewById(R.id.userIDTextInputEditText)

            editText.id = i
            textInputLayout.id = i

            editText.doOnTextChanged { text, start, before, count ->
                viewModel.updateNotes(Notes(viewId = i, text.toString()))
            }

            llMain.addView(view)

            if (notes.isEmpty())
                viewModel.insertNotes(Notes(viewId = i))
        }
    }
}