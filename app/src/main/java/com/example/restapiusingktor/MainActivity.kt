package com.example.restapiusingktor

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.restapiusingktor.ui.theme.RestApiUsingKtorTheme
import com.example.restapiusingktor.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestApiUsingKtorTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel:MainViewModel= hiltViewModel()
                    val rabbit=viewModel.state.value.rabbit
                    val isLoading=viewModel.state.value.isLoading
                    rabbit?.let {
                        Image(painter = rememberImagePainter(
                            data = it.imgUrl,
                            builder = {crossfade(true)}
                        )
                            , contentDescription = "Rabbit Image")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.description, fontWeight = FontWeight.Normal, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(onClick =  viewModel::getRandomRabbit ,
                        modifier = Modifier.align(Alignment.End)
                        ) {
                        Text(text = "Next Rabbit")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if(isLoading){
                        CircularProgressIndicator()
                    }
                }

            }
        }
    }
}

