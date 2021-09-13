import './style.scss'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import LockIcon from '@material-ui/icons/Lock';
import PersonIcon from '@material-ui/icons/Person';

export default function Login() {
    return (
        <>
            <div className='loginContainer'>
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
                        <form noValidate autoComplete="off">

                            <div className='userInput input'>
                                <PersonIcon className='icon' />
                                <TextField id="emailCpf" label="Email ou CPF" />
                            </div>
                            
                            <div className='passwordInput input'>
                                <LockIcon className='icon' />
                                <TextField
                                    id="standard-password-input"
                                    label="Senha"
                                    type="password"
                                    autoComplete="current-password"
                                />
                            </div>
                        </form>
                        <Button variant="contained" color="primary">
                            <LockIcon className='icon' />
                            Entrar
                        </Button>
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