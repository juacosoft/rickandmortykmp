package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil3.compose.AsyncImage
import com.example.shared.domain.entity.CharacterEntity
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.presentation.CharactersViewModel
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.example.shared.Greetings
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val charactersViewModel : CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                CharactersScreen(
                    viewModel = charactersViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(viewModel: CharactersViewModel) {
    val charactersState by viewModel.charactersState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Rick and Morty - Characteres")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Platform: ${Greetings().greet()}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            )
        }
    ) { contentPadings ->
        when(charactersState) {
            is CharactersResult.Loading -> Text("Loading")
            is CharactersResult.Success -> {
                val characters = (charactersState as CharactersResult.Success).characterResponse.characters
                CharactersList(
                    items = characters,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadings)
                )
            }
            is CharactersResult.Error -> Text("Error")
        }

    }
}

@Composable
fun CharactersList(
    items: List<CharacterEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items) { character ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Row {
                    AsyncImage(
                        model = character.image,
                        contentDescription = "Character image ${character.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(150.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.padding(
                            vertical = 4.dp,
                            horizontal = 10.dp
                        )
                    ) {
                        Text(character.name, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                        Text("Specie: ${character.species}", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                        Text("Gender: ${character.gender}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

        }
    }
}