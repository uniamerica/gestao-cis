import React, {useEffect} from "react";
import {
  Box,
  Button,
  Modal,
  Typography,
  TextField,
  Autocomplete,
  Container,
} from "@mui/material";
import { AuthContext } from "./../../Contexts/authContext";
import { useNavigate } from "react-router";
import ImgAgen from "../../assets/images/imageAgend.png"

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  minHeight: 500,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};

const buttonStyle = {
  marginTop: "4%",
  borderRadius: 20,
  backgroundColor: "#00939F",
};

export default function Home() {
  const navigate = useNavigate();
  const { isAuth } = React.useContext(AuthContext);
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  useEffect(() => {
    if (isAuth) {
      return navigate("/home");
    } else {
      return console.log('Credenciais Inválidas');
    }
  }, []);

  return (
    <React.Fragment>
      <Container maxWidth="lg">
        <Box
          name="ImgAgen"
          sx={{
            position: "fixed",
            zIndex: -1,
            maxHeight: "92vh",
            width: "95vw",
            display: "flex",
            justifyContent: "right",
          }}
        >
          <img src={ImgAgen} alt="" />
        </Box>

        <Box sx={{
          maxHeight: "92vh",
          width: "40vw",
          alignItems:"center",
          display: "flex",
          flexDirection:"column",
          justifyContent: "center",

        }}>

          <Typography variant="h5" align="center" marginTop="30%">
            Realize seu agendamento de consulta  <br />
          </Typography>

          <Button
            variant="contained"
            style={buttonStyle}
            onClick={handleOpen}
            disableRipple
          >
            Solicitar
          </Button>

        </Box>

      </Container>

      <Modal
        disableBackdropClick
        open={open} // onClose={handleClose}
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
          >
            <Typography variant="h5" color="initial">
              Solicitação de Agendamento  <br />
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
              type="date"
              id="outlined-required"
              sx={{ marginTop: "1.5rem" }}
            />
            <TextField
              required
              type="time"
              id="outlined-required"
              sx={{ marginTop: "1.5rem" }}
            />
            <Autocomplete
              required
              sx={{ marginTop: "1.5rem" }}
              multiple
              id="tags-outlined"
              options={categoria}
              getOptionLabel={(option) => option.name}
              filterSelectedOptions
              renderInput={(params) => (
                <TextField
                  {...params}
                  label="Categoria"
                  placeholder="Catogoria"
                />
              )}
            />
            <Button
              type="submit"
              variant="contained"
              sx={{ marginTop: "2rem", backgroundColor: "#00939F" }}
            >
              Solicitar
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
    </React.Fragment>
  );
}

const categoria = [
  { id: 0, name: "Acupuntura" },
  { id: 1, name: "Psicologia" },
  { id: 2, name: "Nutrição" },
  { id: 3, name: "Educação Física" },
  { id: 4, name: "Fonoaudiologia" },
  { id: 5, name: "Farmácia" },
  { id: 6, name: "Medicina" },
  { id: 7, name: "Odontologia" },
];