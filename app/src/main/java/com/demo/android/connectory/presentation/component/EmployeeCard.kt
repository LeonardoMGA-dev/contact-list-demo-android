package com.demo.android.connectory.presentation.component

import android.telephony.PhoneNumberUtils
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.demo.android.connectory.R
import com.demo.android.connectory.domain.entity.Employee
import com.valentinilk.shimmer.shimmer
import java.util.*

@Composable
fun EmployeeCard(employee: Employee) {
    val isVisible = remember { MutableTransitionState(false).apply { targetState = true } }
    val formattedNumber =
        PhoneNumberUtils.formatNumber(employee.phoneNumber, Locale.getDefault().country)
    var isExpanded by remember { mutableStateOf(false) }
    val rotateState = animateFloatAsState(
        targetValue = if (isExpanded) 180F else 0F,
    )
    AnimatedVisibility(
        visibleState = isVisible,
        enter = fadeIn(animationSpec = tween(250)),
        exit = fadeOut(animationSpec = tween(250)),
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(employee.photoUrlSmall)
                            .error(R.drawable.ic_launcher_background)
                            .crossfade(true)
                            .build(),
                        loading = {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .size(72.dp)
                            )
                        },
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
                    Spacer(Modifier.weight(1f))
                    Icon(
                        Icons.Default.ArrowDropDown, "",
                        modifier = Modifier.rotate(rotateState.value)
                    )
                }
                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Phone: $formattedNumber", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Email: ${employee.emailAddress}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = employee.biography, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeCardSkeleton() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shimmer()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .shimmer()
                        .size(72.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}