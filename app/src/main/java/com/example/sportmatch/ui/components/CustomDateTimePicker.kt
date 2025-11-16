package com.example.sportmatch.ui.components

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportmatch.ui.theme.ErrorColor
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
/*
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDateTimePicker(
    label: String = "Selecionar data e hora",
    value: LocalDateTime?,
    onValueChange: (LocalDateTime) -> Unit,
    dateTimeFormat: String = "dd/MM/yyyy HH:mm",
    isError: Boolean = false,
    mensagemDeErro: String = ""
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    var showDateDialog by remember { mutableStateOf(false) }
    var showTimeDialog by remember { mutableStateOf(false) }
    var tempDate by remember { mutableStateOf<LocalDateTime?>(null) }

    val formattedText = value?.format(DateTimeFormatter.ofPattern(dateTimeFormat)) ?: ""

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value
            ?.atZone(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()
    )

    val timePickerState = rememberTimePickerState(
        initialHour = value?.hour ?: 12,
        initialMinute = value?.minute ?: 0
    )

        OutlinedTextField(
            value = formattedText,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDateDialog = true },
            trailingIcon = {
                IconButton(onClick = { showDateDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Selecionar data",
                        tint = primaryColor
                    )
                }
            },
            textStyle = TextStyle(fontSize = 16.sp),
            isError = isError
        )

        if (isError && mensagemDeErro.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            if(isError){
                CustomText(mensagemDeErro, TextType.LABEL, color = ErrorColor)
            }
        }
    if (showDateDialog) {
        DatePickerDialog(
            onDismissRequest = { showDateDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val selectedDate = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        tempDate = LocalDateTime.of(selectedDate, LocalTime.NOON)
                        showDateDialog = false
                        showTimeDialog = true
                    }
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDateDialog = false }) { Text("Cancelar") }
            },
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = primaryColor,
                todayDateBorderColor = primaryColor
            )
        ) {
            DatePicker(state = datePickerState)
        }
    }

  if (showTimeDialog && tempDate != null) TimePickerDialog(
        onDismissRequest = { showTimeDialog = false },
        confirmButton = {
            TextButton(onClick = {
                val pickedTime = LocalTime.of(
                    timePickerState.hour,
                    timePickerState.minute
                )
                val newDateTime = LocalDateTime.of(tempDate!!.toLocalDate(), pickedTime)
                onValueChange(newDateTime)
                showTimeDialog = false
            }) { Text("OK") }
        },
        title = { Text("") },
        dismissButton = {
            TextButton(onClick = { showTimeDialog = false }) { Text("Cancelar") }
        }
    ) {
        TimePicker(
            state = timePickerState,
            colors = TimePickerDefaults.colors(
                timeSelectorSelectedContainerColor = primaryColor
            )
        )
    }
}*/