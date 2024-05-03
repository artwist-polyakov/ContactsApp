package com.master.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.master.contactsapp.models.Contact

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld()
        }

    }

    @Composable
    fun RoundInitials(contact: Contact) {
        val initials = contact.name.first().toString() + contact.familyName.first().toString()
        Box{
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                ,
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = initials
            )
        }
    }

    @Composable
    fun ShowPhoto(contact: Contact) {
        val image = contact.imageRes?.let { painterResource(id = it) }
        Box {
            image?.let {
                Image(
                    modifier = Modifier
                        .size(240.dp, 120.dp)
                        .align(Alignment.Center)
                    ,
                    painter = it,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }

    @Composable
    private fun HelloWorld() {
        Text("Jetpack Compose")
    }


    @Preview(
        name = "portrait",
        showSystemUi = true,
    )
    @Composable
    private fun ShowPhoto() {
        ShowPhoto(
            Contact(
                name = "John",
                familyName = "Doe",
                phone = "123456789",
                address = "New York",
                imageRes = R.drawable.some_photo_from_internet
            )
        )
    }

    @Preview(
        name = "portrait",
        showSystemUi = true,
    )
    @Composable
    private fun RoundInitials() {
        RoundInitials(
            Contact(
                name = "John",
                familyName = "Doe",
                phone = "123456789",
                address = "New York"
            )
        )
    }

}

