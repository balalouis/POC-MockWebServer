package com.clean.poc.poc_mockwebserver

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.clean.poc.poc_mockwebserver.databinding.ActivityMainBinding
import com.clean.poc.poc_mockwebserver.databinding.FragmentRecipeBinding
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {

    private lateinit var recipeDetailViewModel: RecipeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createViewModel()
        fetchRecipe()
    }

    private fun createViewModel() {
        val recipeRepository =
            RecipeRepository(NetworkModule.provideRecipeService(), RecipeMapper())
        val token = NetworkModule.provideAuthToken()
        recipeDetailViewModel = ViewModelProvider(
            this,
            RecipeViewModelFactory(recipeRepository, token)
        )[RecipeViewModel::class.java]
    }

    private fun fetchRecipe() {
        lifecycleScope.launch {
            recipeDetailViewModel.fetchUserList(2, "beef")
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                recipeDetailViewModel._uiState.collect {
                    when (it) {
                        is RecipeUIState.Success -> {
                            Log.i("-----> ", "" + it.userList)
                            if (it.userList!!.isNotEmpty()) {
                                updateUserListAdapter(applicationContext, it.userList)
                            }
                        }
                        is RecipeUIState.Failure -> {
                            Log.i("-----> ", "Failure")
                        }
                    }
                }
            }
        }
    }

    private fun updateUserListAdapter(context: Context, userList: List<Recipe>?) {
        val userListAdapter = userList?.let { RecipeListAdapter(userList) }
        val userListRecyclerView = binding.myRecyclerView
        userListRecyclerView.adapter = userListAdapter
        userListRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}