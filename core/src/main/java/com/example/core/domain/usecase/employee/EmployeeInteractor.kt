package com.example.core.domain.usecase.employee

import com.example.core.data.Resource
import com.example.core.data.remote.response.Origin
import com.example.core.domain.model.PenggunaModel
import com.example.core.domain.model.employee.EmployeeModel
import com.example.core.domain.model.staff.ListStaffModel
import com.example.core.domain.model.staff.StaffModel
import com.example.core.domain.repository.IEmployeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeInteractor @Inject constructor(val repository: IEmployeeRepository) :
    EmployeeUseCase {

//    override fun getEmployee(
//        origin: Origin,
//        employeeId: Int
//    ): Flow<Resource<List<EmployeeModel>>> {
//        return repository.getEmployee(origin, employeeId)
//    }
//    override fun getAllEmployee(origin: Origin): Flow<Resource<List<EmployeeModel>>> {
//        return repository.getAllEmployee(origin)
//    }
//
//    override fun getDataStaff(
//        origin: Origin
//    ): Flow<Resource<StaffModel>> {
//        return repository.getDataStaff(origin)
//    }
//
//
//    override fun getListDataStaff(
//        origin: Origin
//    ): Flow<Resource<List<ListStaffModel>>> {
//        return repository.getListDataStaff(origin)
//    }
//
//    override fun getPengguna(
//        origin: Origin
//    ): Flow<Resource<List<PenggunaModel>>> {
//        return repository.getPengguna(origin)
//    }


}