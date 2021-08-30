import styled from "styled-components"
import Roboto from '../../assets/fonts/Roboto-Regular.ttf'

export const HeaderStyle = styled.header`
    height: 80px;
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    border: 1px solid #DDDDDD;
    -webkit-box-shadow: 0px 1px 5px 0px rgba(0,0,0,0.25);
    -moz-box-shadow: 0px 1px 5px 0px rgba(0,0,0,0.25);
    box-shadow: 0px 1px 5px 0px rgba(0,0,0,0.25);
    @font-face {
        font-family: 'Roboto';
        src: url(${Roboto});
    }
`

export const LogoContainer = styled.div`
    width: 280px;
    height: 80px;
    display: flex;
    justify-content: center;

    .logo {
        width: 195px;
        height: 48px;
        margin-top: auto;
        margin-bottom: auto;
    }
`

export const OtherContent = styled.div`
    height: 80px;
    width: 17%;
    /* background-color: #098765; */ 
    display: flex;
    margin-top: auto;
    margin-bottom: auto;

    .icon {
        margin-top: auto;
        margin-bottom: auto;
        margin-left: 10px;
        color: #333;
    }

    .greeting {
        margin-top: auto;
        margin-bottom: auto;
        margin-left: 10px;
        font-family: Roboto, sans-serif;
        font-style: normal;
        font-weight: normal;
        font-size: 16px;
        line-height: 20px;
        color: #333;

    }
`