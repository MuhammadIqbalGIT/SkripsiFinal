package com.example.myapplicationskripsiiqbal3.ui.package1

import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.staff.StaffModel
import com.example.core.domain.usecase.employee.EmployeeUseCase
import com.example.myapplicationskripsiiqbal3.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val useCase: EmployeeUseCase,
    //val session: Session
) : BaseViewModel() {


    //Employee
    fun getEmployee(employeeId: Int) = useCase.getEmployee(origin, employeeId)


    fun getAllEmployee() = useCase.getAllEmployee(origin)




    //staff
    private var _getDataStaffState: MutableStateFlow<Resource<StaffModel>> =
        MutableStateFlow(Resource.Standby())
    val getDataStaffState: StateFlow<Resource<StaffModel>> =
        _getDataStaffState


    fun setStandBygetDataStaff() {
        _getDataStaffState.value = Resource.Standby()
    }

    fun getDataStaff() = viewModelScope.launch {
        useCase.getDataStaff(origin).collect {
            _getDataStaffState.value = it
        }
    }


    //bentuk list
    fun getListDataStaff() = useCase.getListDataStaff(origin)



    fun getPengguna() = useCase.getPengguna(origin)

}
