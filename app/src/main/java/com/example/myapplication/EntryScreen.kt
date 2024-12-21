package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EntryScreen(modifier: Modifier = Modifier) {
    EntryContent()
}

@Composable
private fun EntryContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ){
    Image(
        painter = painterResource(R.drawable.onboarding),
        contentDescription = "Onboarding Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.matchParentSize(),
    )
        Surface(
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxSize()
        ) {

            Column(modifier = Modifier.fillMaxSize().padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) { // .wrapContentSize(Alignment.Center)
            Text(
                text = "Don't know what to cook?\n Let's find out!",
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp,
                color = Color.White
            )
            Spacer(
                modifier = Modifier.size(46.dp),

            )
            Text(
                text = "Find the best recipes for cooking",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(
                modifier = Modifier.size(16.dp),
                )

            Button(onClick =  { Log.d("AQUI","AQUI")},
                modifier = Modifier
                .padding(top = 8.dp, bottom = 32.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )

            ) {
                Text(
                    text = "Start cooking",
                    color = Color.White
                )
            }


    }}}

}

@Preview(showBackground = true)
@Composable
fun EntryContentPreview() {
    EntryContent()
}