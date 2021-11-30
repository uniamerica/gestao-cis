import * as React from "react";
import { Fragment } from "react";
import {
  Box,
  Button,
  Modal,
  Typography,
  TextField,
  Autocomplete,
  Container,
} from "@mui/material";
import CustomizedTables from "../../Components/GenericUserTable";

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  minHeight: 600,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};

const buttonStyle = {
  marginTop: "2%",
  borderRadius: 20,
  backgroundColor: "#00939F",
};

export default function Professional() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <Fragment>
      <Container maxWidth="lg">
        <Button
          variant="contained"
          style={buttonStyle}
          onClick={handleOpen}
          disableRipple
        >
          Cadastrar
        </Button>
        <CustomizedTables />
      </Container>
      <Modal
        disableBackdropClick
        open={open}
        // onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={modalStyle}>
          <Box
            component="form"
            sx={{
              display: "flex",
              flexDirection: "column",
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
              required
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
              sx={{ marginTop: "2rem", backgroundColor: "#00939F" }}
            >
              Cadastrar
            </Button>
            <Button
              type="reset"
              variant="contained"
              onClick={handleClose}
              sx={{ marginTop: "2rem", backgroundColor: "#c3c3c3" }}
            >
              Cancelar
            </Button>
          </Box>
        </Box>
      </Modal>
    </Fragment>
  );
}

const especialidades = [
  { id: 0, name: "Acupuntura" },
  { id: 1, name: "Psicologia" },
  { id: 2, name: "Nutrição" },
  { id: 3, name: "Educação Física" },
  { id: 4, name: "Fonoaudiologia" },
  { id: 5, name: "Farmácia" },
  { id: 6, name: "Medicina" },
  { id: 7, name: "Odontologia" },
];
