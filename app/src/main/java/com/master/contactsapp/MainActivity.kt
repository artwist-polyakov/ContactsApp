package com.master.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.master.contactsapp.models.Contact

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactInfo(
                Contact(
                    name = "John",
                    familyName = "Doe",
                    phone = "123456789",
                    address = "New York",
                    imageRes = R.drawable.some_photo_from_internet
                )
            )
        }

    }

    @Composable
    fun RoundInitials(contact: Contact) {
        val initials = contact.name.first().toString() + contact.familyName.first().toString()
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                modifier = Modifier
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = initials,
                fontWeight = FontWeight.Bold,
            )
        }
    }

    @Composable
    fun ShowPhoto(contact: Contact) {
        val image = contact.imageRes?.let { painterResource(id = it) }
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        )
        {
            image?.let {
                Image(
                    modifier = Modifier
                        .size(96.dp, 48.dp)
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
    private fun ShowFamilyName(contact: Contact) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = contact.familyName,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
            )
            if (contact.isFavorite) {
                Image(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
        }

    }

    @Composable
    private fun NameAndSurename(contact: Contact) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${contact.name} ${contact.surname?.let { it } ?: ""}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            ShowFamilyName(contact)
        }
    }

    @Composable
    private fun NameAndPhoto(contact: Contact) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (contact.imageRes == null) {
                RoundInitials(contact)
            } else {
                ShowPhoto(contact)
            }
            NameAndSurename(contact)
        }
    }

    @Composable
    private fun LabelAndValueRow(label: String, value: String) {
        Row(
            modifier =
            Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(0.5f)
                    .wrapContentWidth(Alignment.End)

            )
            Text(
                text = value,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(0.5f)
            )
        }
    }


    @Composable
    private fun DetailedContactInfo(contact: Contact) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabelAndValueRow("Phone: ", contact.phone)
            LabelAndValueRow("Adress: ",contact.address)
            contact.email?.let {
                LabelAndValueRow("Email: ", it)
            }
        }
    }

    @Composable
    private fun ContactInfo(contact: Contact) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NameAndPhoto(contact)
            DetailedContactInfo(contact)
        }
    }


    @Preview(
        name = "portrait",
        showSystemUi = true,
    )
    @Composable
    private fun ShowPhoto() {
        ContactInfo(
            Contact(
                name = "John",
                familyName = "Doe",
                phone = "123456789",
                address = "New York",
                imageRes = R.drawable.some_photo_from_internet,
                isFavorite = true
            )
        )
    }

    @Preview(
        name = "portrait",
        showSystemUi = true,
    )
    @Composable
    private fun RoundInitials() {
        ContactInfo(
            Contact(
                name = "John",
                familyName = "Doe",
                phone = "123456789",
                address = "New York dcas wafsd waedcs aedf asdc ewd sae fvsd",
                isFavorite = false,
                email = "sadf@asdf.d"
            )
        )
    }

}

