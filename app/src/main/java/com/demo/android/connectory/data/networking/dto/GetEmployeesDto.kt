package com.demo.android.connectory.data.networking.dto

import com.demo.android.connectory.domain.entity.Employee
import com.google.gson.annotations.SerializedName

data class GetEmployeesResponseDto(
    val employees: List<EmployeeDto>
)

data class EmployeeDto(
    val uuid: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("email_address")
    val emailAddress: String,
    val biography: String,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String,
    val team: String,
    @SerializedName("employee_type")
    val employeeType: String,
)

fun EmployeeDto.toDomainEntity(): Employee {
    return Employee(
        uuid,
        fullName,
        phoneNumber,
        emailAddress,
        biography,
        photoUrlSmall,
        photoUrlLarge,
        team,
        employeeType
    )
}