package com.demo.android.connectory.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.demo.android.connectory.R
import com.demo.android.connectory.domain.entity.Employee

@Composable
fun EmployeeCard(employee: Employee) {
    // TODO: implement expanding animation
    // TODO: Add chevron
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(employee.photoUrlSmall)
                        .error(R.drawable.ic_launcher_background)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Employee photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(72.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = employee.fullName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(text = employee.team, fontSize = 14.sp)
                }
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Phone: ${employee.phoneNumber}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${employee.emailAddress}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Bio: ${employee.biography}", fontSize = 14.sp)
            }
        }
    }
}