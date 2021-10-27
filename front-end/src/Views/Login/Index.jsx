import './style.scss'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import LockIcon from '@material-ui/icons/Lock';
import PersonIcon from '@material-ui/icons/Person';
import Header from '../../Components/Header/Header';
import { useState } from 'react'

import api from '../../services/api'

export default function Login() {
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const loginInfo = {
        username: user,
        password: password
    }
    async function handleSubmit(e) {
        e.preventDefault();
        // console.log(loginInfo);
        await logar(loginInfo);
    }

    async function logar(loginInfo) {
        console.log(loginInfo);
        const response = await api.post(
            '/admin/authenticate',
            loginInfo
        )
        const data = response.data
        console.log(data)
    }

    return (
        <>
            <div className='loginContainer'>
                <Header name="Visitante" />
                <div className='loginCard'>
                    <div className='loginHeader'>
                        <section className='cisText'>
                            <h4>
                                Centro<br/>Integrado
                            </h4>
                            <p>
                                de Saúde
                            </p>
                        </section>
                        <section className='loginText'>
                            <h3>
                                Login
                            </h3>
                        </section>
                    </div>
                    <div className='loginBody'>
                        <form
                            // noValidate 
                            autoComplete="off"
                            onSubmit={(e) => handleSubmit(e)}
                        >

                            <div className='userInput input'>
                                <PersonIcon className='icon' />
                                <TextField
                                    id="emailCpf"
                                    label="Email ou CPF"
                                    onChange={value => setUser(value.target.value)}
                                    value={user}
                                />
                            </div>
                            
                            <div className='passwordInput input'>
                                <LockIcon className='icon' />
                                <TextField
                                    id="standard-password-input"
                                    label="Senha"
                                    type="password"
                                    autoComplete="current-password"
                                    onChange={value => setPassword(value.target.value)}
                                    value={password}
                                />
                            </div>
                            <Button
                                variant="contained"
                                id="button" 
                                // style={{backgroundColor: '#1d2366', color: '#FFF', width:'150px', margin: 'auto'}}
                                type='submit'
                            >
                                {/* <LockIcon className='icon' /> */}
                                Entrar
                            </Button>
                        </form>
                    </div>
                    <div className='loginFooter'>
                        <div className='text-center'>
                            <a href='#'>Esqueci minha senha</a>
                        </div>
                        <div className='text-center'>
                            <p>Ainda não tem conta?</p>
                            <a href=''>Crie a sua conta</a>
                        </div>

                    </div>
                </div>
            </div>
        </>
    );
}