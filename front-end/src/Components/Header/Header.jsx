import { Fragment } from 'react'
import Logo from '../../assets/images/logo.png';
import { Avatar, Container, Typography, Box } from '@mui/material'

export default function Header() {
    return (
        <Fragment>
            <Box className="header"
                sx={{
                    height: '60px',
                    position: 'sticky',
                    top: '0',
                    backgroundColor: '#FFFF',
                    boxShadow: '8px 2px 10px #dedede',
                    padding: '12px',
                    zIndex: '999'
                }}>
                <Container maxWidth="lg"
                    sx={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between'
                    }}>
                    <img src={Logo} height={60} />
                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                        <Typography variant="h6">Olá Visitante</Typography>
                        <Avatar sx={{ marginLeft: '12px' }}>V</Avatar>
                    </Box>
                </Container>
            </Box>
        </Fragment>
    )
}