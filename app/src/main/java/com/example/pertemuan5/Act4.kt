package com.example.pertemuan5

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDataDiri(modifier: Modifier = Modifier) {
    // ==== State input ====
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var expExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    var skillAndroid by remember { mutableStateOf(false) }
    var skillUiUx by remember { mutableStateOf(false) }
    var skillWeb by remember { mutableStateOf(false) }

    // ==== State untuk menyimpan data ====
    var savedNama by remember { mutableStateOf("") }
    var savedEmail by remember { mutableStateOf("") }
    var savedTgl by remember { mutableStateOf("") }
    var savedExp by remember { mutableStateOf("") }
    var savedSkills by remember { mutableStateOf("") }

    val expOptions = stringArrayResource(id = R.array.experience_options)

    // ==== LOGIKA TOMBOL ====
    val isFormValid = namaDepan.isNotEmpty() &&
            email.isNotEmpty() &&
            tglLahir.isNotEmpty() &&
            experience.isNotEmpty() &&
            (skillAndroid || skillUiUx || skillWeb)

    // ==== Scaffold ====
    Scaffold(
        containerColor = colorResource(id = R.color.transparent)
    ) { innerPadding ->

        // Box: supaya Card bisa mengapung di atas Image header
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // ====== GAMBAR HEADER ======
            Image(
                painter = painterResource(id = R.drawable.bg_header),
                contentDescription = stringResource(id = R.string.cd_header),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.header_height)),
                contentScale = ContentScale.Crop
            )

            // ====== CARD MENGAPUNG ======
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.header_height) - dimensionResource(id = R.dimen.card_overlap))
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(id = R.dimen.card_elevation)
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_large))
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // TITLE
                    Text(
                        text = stringResource(id = R.string.form_title),
                        fontSize = dimensionResource(id = R.dimen.title_size).value.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_primary)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

                    // SUBTITLE
                    Text(
                        text = stringResource(id = R.string.form_subtitle),
                        fontSize = dimensionResource(id = R.dimen.subtitle_size).value.sp,
                        color = colorResource(id = R.color.grey_hint),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))

                    // ROW NAMA
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                    ) {
                        CustomTextField(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            placeholder = stringResource(id = R.string.hint_nama_depan),
                            modifier = Modifier.weight(1f)
                        )
                        CustomTextField(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            placeholder = stringResource(id = R.string.hint_nama_belakang),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

                    // EMAIL
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = stringResource(id = R.string.hint_email),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

                    // ROW TGL LAHIR & EXPERIENCE
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                    ) {
                        // Tgl Lahir
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen.field_height))
                                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.field_corner)))
                                    .border(
                                        width = dimensionResource(id = R.dimen.field_border),
                                        color = colorResource(id = R.color.grey_border),
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.field_corner))
                                    )
                                    .clickable { showDatePicker = true }
                                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (tglLahir.isEmpty()) stringResource(id = R.string.hint_tgl_lahir) else tglLahir,
                                    fontSize = dimensionResource(id = R.dimen.label_size).value.sp,
                                    color = colorResource(id = R.color.grey_hint)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = stringResource(id = R.string.cd_icon_calendar),
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size))
                                )
                            }
                        }

                        // Experience Dropdown
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen.field_height))
                                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.field_corner)))
                                    .border(
                                        width = dimensionResource(id = R.dimen.field_border),
                                        color = colorResource(id = R.color.grey_border),
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.field_corner))
                                    )
                                    .clickable { expExpanded = true }
                                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (experience.isEmpty()) stringResource(id = R.string.hint_experience) else experience,
                                    fontSize = dimensionResource(id = R.dimen.label_size).value.sp,
                                    color = colorResource(id = R.color.grey_hint)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black)
                                )
                            }

                            DropdownMenu(
                                expanded = expExpanded,
                                onDismissRequest = { expExpanded = false }
                            ) {
                                expOptions.forEach { opt ->
                                    DropdownMenuItem(
                                        text = { Text(opt) },
                                        onClick = {
                                            experience = opt
                                            expExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))

                    // LABEL SKILL
                    Text(
                        text = stringResource(id = R.string.label_skill),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = dimensionResource(id = R.dimen.label_size).value.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

                    // CHECKBOX SKILL
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SkillCheckbox(label = stringResource(id = R.string.skill_android), checked = skillAndroid, onCheckedChange = { skillAndroid = it })
                        SkillCheckbox(label = stringResource(id = R.string.skill_uiux), checked = skillUiUx, onCheckedChange = { skillUiUx = it })
                        SkillCheckbox(label = stringResource(id = R.string.skill_web), checked = skillWeb, onCheckedChange = { skillWeb = it })
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))

                    HorizontalDivider(
                        modifier = Modifier.padding(
                            bottom = dimensionResource(id = R.dimen.padding_medium),
                            top = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        thickness = dimensionResource(id = R.dimen.field_border),
                        color = colorResource(id = R.color.grey_border)
                    )
                    Button(
                        onClick = {
                            savedNama = "$namaDepan $namaBelakang"
                            savedEmail = email
                            savedTgl = tglLahir
                            savedExp = experience

                            val skills = mutableListOf<String>()
                            if (skillAndroid) skills.add("Android")
                            if (skillUiUx) skills.add("UI/UX")
                            if (skillWeb) skills.add("Web")
                            savedSkills = skills.joinToString(", ")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.button_height)),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.purple_primary),
                            contentColor = colorResource(id = R.color.white),
                            disabledContainerColor = colorResource(id = R.color.grey_disabled),
                            disabledContentColor = colorResource(id = R.color.grey_text_disabled)
                        ),
                        enabled = isFormValid
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_proceed),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            bottom = dimensionResource(id = R.dimen.padding_medium),
                            top = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        thickness = dimensionResource(id = R.dimen.field_border),
                        color = colorResource(id = R.color.grey_border)
                    )
                    ElevatedCard (
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.black)),
                        modifier = Modifier
                            .width(300.dp) // Hapus height agar auto menyesuaikan teks
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
                            Text(text = stringResource(id = R.string.result_nama, savedNama), color = colorResource(id = R.color.white), fontSize = 12.sp)
                            Text(text = stringResource(id = R.string.result_email, savedEmail), color = colorResource(id = R.color.white), fontSize = 12.sp)
                            Text(text = stringResource(id = R.string.result_tgl, savedTgl), color = colorResource(id = R.color.white), fontSize = 12.sp)
                            Text(text = stringResource(id = R.string.result_exp, savedExp), color = colorResource(id = R.color.white), fontSize = 12.sp)
                            Text(text = stringResource(id = R.string.result_skill, savedSkills), color = colorResource(id = R.color.white), fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))
                }
            }

            // DIALOG DATE PICKER
            if (showDatePicker) {
                val datePickerState = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                    tglLahir = formatter.format(Date(millis))
                                }
                                showDatePicker = false
                            }
                        ) {
                            Text(text = stringResource(id = R.string.dialog_ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text(text = stringResource(id = R.string.dialog_cancel))
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}
