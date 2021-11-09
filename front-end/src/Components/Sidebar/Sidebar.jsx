import CalendarTodayOutlinedIcon from '@material-ui/icons/CalendarTodayOutlined'
import PeopleAltOutlinedIcon from '@material-ui/icons/PeopleAltOutlined'
import doctor from '../../assets/images/doctor.png'
import PhoneOutlinedIcon from '@material-ui/icons/PhoneOutlined'
import MonetizationOnOutlinedIcon from '@material-ui/icons/MonetizationOnOutlined'
import { SidebarStyle } from './style'

export default function Sidebar() {
    return(
        <SidebarStyle>
            <div className='content' >
                <div className='combo'>
                    <CalendarTodayOutlinedIcon className='icon' />
                    <p className='title'>Agendamentos</p>
                </div>
                <div className='combo'>
                    <PeopleAltOutlinedIcon className='icon' />
                    <p className='title'>Pacientes</p>
                </div>
                <div className='combo'>
                    <img className='doctor' src={doctor} />
                    <p className='title'>Profissionais</p>
                </div>
                <div className='combo'>
                    <PhoneOutlinedIcon className='icon' />
                    <p className='title'>Secretárias</p>
                </div>
                <div className='combo'>
                    <MonetizationOnOutlinedIcon className='icon' />
                    <p className='title'>Pagamentos</p>
                </div>
            </div>
        </SidebarStyle>
    )
}