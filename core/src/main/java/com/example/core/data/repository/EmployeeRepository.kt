package com.example.core.data.repository

import com.example.core.data.OnlyNetworkBoundResource
import com.example.core.data.Resource
import com.example.core.data.remote.datasource.EmployeeDataSource
import com.example.core.data.remote.response.ApiResponse
import com.example.core.data.remote.response.Origin
import com.example.core.domain.model.PenggunaModel
import com.example.core.domain.model.employee.EmployeeModel
import com.example.core.domain.model.staff.ListStaffModel
import com.example.core.domain.model.staff.StaffModel
import com.example.core.domain.repository.IEmployeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeRepository @Inject constructor(val source: EmployeeDataSource) :
    IEmployeeRepository {
    override fun getEmployee(
        origin: Origin,
        employeeId: Int
    ): Flow<Resource<List<EmployeeModel>>> {
        return object : OnlyNetworkBoundResource<List<EmployeeModel>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<EmployeeModel>>> {
                return source.getEmployee(origin, employeeId)
            }
        }.asFlow()
    }


    override fun getAllEmployee(origin: Origin): Flow<Resource<List<EmployeeModel>>> {
        return object : OnlyNetworkBoundResource<List<EmployeeModel>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<EmployeeModel>>> {
                return source.getAllEmployee(origin)
            }
        }.asFlow()
    }

    override fun getDataStaff(
        origin: Origin
    ): Flow<Resource<StaffModel>> {
        return object : OnlyNetworkBoundResource<StaffModel>() {
            override suspend fun createCall(): Flow<ApiResponse<StaffModel>> {
                return source.getDataStaff(origin)
            }
        }.asFlow()
    }


    override fun getListDataStaff(
        origin: Origin
    ): Flow<Resource<List<ListStaffModel>>> {
        return object : OnlyNetworkBoundResource<List<ListStaffModel>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<ListStaffModel>>> {
                return source.getListDataStaff(origin)
            }
        }.asFlow()
    }

    override fun getPengguna(
        origin: Origin
    ): Flow<Resource<List<PenggunaModel>>> {
        return object : OnlyNetworkBoundResource<List<PenggunaModel>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<PenggunaModel>>> {
                return source.getPengguna(origin)
            }
        }.asFlow()
    }


}