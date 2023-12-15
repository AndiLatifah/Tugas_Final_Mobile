package com.d121211086.Doaapp.ui.aktivities.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211086.Doaapp.data.models.Doa
import com.d121211086.Doaapp.ui.aktivities.detail.DetailActivity
import com.d121211086.Doaapp.ui.theme.DoaAPPTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoaAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar (
                            title = {
                                Text(
                                    text = "Doa-Doa",
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        )
                        val mainViewModels: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListDoaScreen(mainViewModels.mainUiState)
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun ListDoaScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Box (
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (mainUiState) {
                is MainUiState.Loading -> Text(text = "Loading...", fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
                is MainUiState.Error -> Text(text = "Error Found", fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
                is MainUiState.Success -> ListDoaDoa(
                    doa = mainUiState.Doa,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    @Composable
    fun ListDoaDoa(doa: List<Doa>, modifier: Modifier = Modifier){
        LazyColumn(modifier = modifier){
            items(doa){data ->
                DoaItem(doa = data)
            }
        }
    }
    @Composable
    fun DoaItem(doa: Doa){
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clickable {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("DOA", doa)
                    context.startActivity(intent)
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = doa.doa.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }