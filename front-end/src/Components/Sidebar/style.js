import styled from 'styled-components'
import Roboto from '../../assets/fonts/Roboto-Regular.ttf'

export const SidebarStyle = styled.div`
    width: 250px;
    height: 100vh;
    background-color: #1CA78C;
    color: #FFF;
    @font-face {
        font-family: 'Roboto';
        src: url(${Roboto});
    }

    .content{ 
        display: flex;
        flex-direction: column;
        align-content: space-around;
        padding-top: 20px;
    }

    .icon {
        margin: 25px 15px 25px 20px;
    }

    .title{
        font-family: Roboto, sans-serif;
        font-size: 15px;
        display: flex;
        align-self: center;
    }

    .combo {
        display: flex;
        flex-direction: row;
    }

    .doctor {
        margin: 25px 14px 25px 20px;
        width: 10%;
    }
`