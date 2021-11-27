import { TextField, Button, Container, Box, Typography, Tab, Tabs, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio } from '@mui/material';
import React, { Fragment, useState } from 'react'
import PropTypes from 'prop-types';
import Background from '../../assets/images/medicineBg.svg'
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

// PAINEIS START -----------------
function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box sx={{ p: 3 }}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.number.isRequired,
    value: PropTypes.number.isRequired,
};

function a11yProps(index) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

// PAINEIS END -----------------

export default function Login() {
    // PAINEIS
    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    // LOGIN
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const loginInfo = {
        user: user,
        password: password
    }

    function handleSubmit(e) {
        e.preventDefault();
        console.log(loginInfo);
    }


    return (
        <Fragment>
            <Container maxWidth="lg"
                sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    paddingBottom: '3rem',
                    boxShadown: 'cccccc8f 8px 6px 4px'
                }}>
                <Box name="BackgroundImage"
                    sx={{
                        position: 'fixed',
                        zIndex: -1,
                        maxHeight: '92vh',
                        width: '100vw',
                        display: 'flex',
                        justifyContent: 'center'
                    }}>
                    <img src={Background} alt="" />
                </Box>
                <Box name="Card"
                    sx={{
                        width: 600,
                        backgroundColor: '#ffff',
                        marginTop: '10%',
                        padding: '1rem',
                        borderRadius: '8px'
                    }}>
                    <Typography variant="h5" sx={{ textAlign: 'center' }}>Centro Integrado de Saúde</Typography>
                    <Box
                        sx={{
                            display: 'flex',
                            justifyContent: 'center'
                        }}>
                        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                            <Tab label="Login" {...a11yProps(0)} />
                            <Tab label="Cadastre-se" {...a11yProps(1)} />
                        </Tabs>
                    </Box>
                    <TabPanel value={value} index={0}>
                        <div name="FormLogin"
                            style={{
                                width: '100%',
                                height: '100%',
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center'
                            }}>
                            <Box component="form" sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                width: '80%',
                                justifyContent: 'center'
                            }}>
                                <AccountCircleIcon sx={{ alignSelf: 'center', fontSize: '60px' }} />
                                <Typography variant="h5" color="initial">Já tem conta? <br />Entre com seu email e senha!</Typography>
                                <TextField
                                    required
                                    type="email"
                                    id="outlined-required"
                                    label="Email"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField required id="outlined-required" label="Senha" sx={{ marginTop: '1.5rem' }} type="password" />
                                <Button variant="contained" sx={{ marginTop: '2rem' }}>Login</Button>
                            </Box>
                        </div>
                    </TabPanel>
                    <TabPanel value={value} index={1}>
                        <div name="FormCadastro"
                            style={{
                                width: '100%',
                                height: '100%',
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center'
                            }}>
                            <Box component="form"
                                sx={{
                                    display: 'flex',
                                    flexDirection: 'column',
                                    width: '80%',
                                    justifyContent: 'center'
                                }}>
                                <Typography variant="h5" color="initial" sx={{ textAlign: 'center' }}>Olá! Estamos muito felizes em ter você por aqui</Typography>
                                <Typography variant="h6" color="initial" sx={{ marginTop: '32px' }}>Para realizar seu cadastro, preencha os campos abaixo:</Typography>
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="Nome Completo"
                                    sx={{ marginTop: '1.5rem' }} />
                                <div
                                    style={{
                                        display: 'flex',
                                        alignItems: 'center',
                                        justifyContent: 'space-between'
                                    }}>
                                    <TextField
                                        required
                                        type="date"
                                        id="outlined-required"
                                        label="Data de Nascimento"
                                        value={'2000-01-01'}
                                        sx={{ marginTop: '1.5rem' }} />
                                    <FormControl component="fieldset" sx={{ marginTop: '1.5rem' }}>
                                        <FormLabel component="legend">Gênero</FormLabel>
                                        <RadioGroup>
                                            <FormControlLabel value="M" control={<Radio />} label="Masculino" />
                                            <FormControlLabel value="F" control={<Radio />} label="Feminino" />
                                        </RadioGroup>
                                    </FormControl>
                                </div>
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="CPF"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="RG"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="email"
                                    id="outlined-required"
                                    label="Email"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="Telefone"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="Rua"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="Cidade"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="Bairro"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="País"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="UF"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="Número"
                                    sx={{ marginTop: '1.5rem' }} />
                                <TextField
                                    required
                                    type="text"
                                    id="outlined-required"
                                    label="CEP"
                                    sx={{ marginTop: '1.5rem' }} />
                                <Button variant="contained" sx={{ marginTop: '2rem' }}>Cadastrar</Button>
                            </Box>
                        </div>
                    </TabPanel>
                </Box>
            </Container>
        </Fragment>
    );
}