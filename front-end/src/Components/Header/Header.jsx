import { HeaderStyle, LogoContainer, OtherContent } from "./style";
import Logo from '../../assets/images/logo.png'

import NotificationsOutlinedIcon from '@material-ui/icons/NotificationsOutlined'
import AccountCircleIcon from '@material-ui/icons/AccountCircle'

export default function Header(props) {
    return(
        <HeaderStyle>
            <LogoContainer>
                <img className='logo' alt='Logotipo Centro Integrado de Saúde UniAmérica' src={Logo} />
            </LogoContainer>
            <OtherContent>
                <NotificationsOutlinedIcon className='icon' />
                <p className='greeting' >Olá, {props.name}</p>
                <AccountCircleIcon className='icon' />
            </OtherContent>
        </HeaderStyle>
    )
}