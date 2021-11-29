import * as React from 'react';
import {Fragment} from 'react'
import {Box, Button, Modal, Typography, TextField, Autocomplete } from "@mui/material";

const modalStyle = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  overflow: 'scroll',
  transform: 'translate(-50%, -50%)',
  width: 400,
  height: 600,
  bgcolor: 'background.paper',  
  boxShadow: 24,
  p: 4,
};

const buttonStyle = {
    marginTop: '2%',
    left: '75%',
    borderRadius: 20,
    backgroundColor: '#00939F',
    boxShadow: 'none',
    textTransform: 'none'
}

export default function Professional() {
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // name: "",
    // email: "",
    // phone: "",
    // username: "",
    // password: "",
    // professionalDocument: "",
    // specialy: ["",""]
    
    return(
        <Fragment>
            <Button variant='contained' style={buttonStyle} onClick={handleOpen} disableRipple>Cadastrar</Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={modalStyle}>
                <Box
                component="form"
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  width: "80%",
                  justifyContent: "center",
                }}
                // onSubmit={onSubmit}
              >
                <Typography variant="h5" color="initial">
                  Novo profissional <br />
                </Typography>
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Nome"
                  sx={{ marginTop: "1.5rem" }}
                  />
                <TextField
                  required
                  type="phone"
                  id="outlined-required"
                  label="Telefone"
                  sx={{ marginTop: "1.5rem" }}
                  />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Número de documento"
                  sx={{ marginTop: "1.5rem" }}
                />
                <Autocomplete
                sx={{ marginTop: "1.5rem" }}
                multiple
                id="tags-outlined"
                options={especialidades}
                getOptionLabel={(option) => option.name}
                filterSelectedOptions
                renderInput={(params) => (
                  <TextField
                    {...params}
                    label="Especialidades"
                    placeholder="Especialidades"
                  />
                )}
              />
                <TextField
                  required
                  type="email"
                  id="outlined-required"
                  label="Email"
                  sx={{ marginTop: "1.5rem" }}
                  //   {...register('email')}
                />
                <TextField
                  required
                  id="outlined-required"
                  label="Senha"
                  sx={{ marginTop: "1.5rem" }}
                  type="password"
                //   {...register('password')}
                />
                <Button
                  type="submit"
                  variant="contained"
                  sx={{ marginTop: "2rem", backgroundColor: '#00939F' }}
                >
                  Cadastrar
                </Button>
              </Box>
                </Box>
            </Modal>
        </Fragment>
    )
}

const especialidades = [{
  id: 1, name: 'Acupuntura'},
  {id: 2, name: 'Psicologia'},
  {id: 3, name: 'Nutrição'},
  {id: 4, name: 'Num sei'}];