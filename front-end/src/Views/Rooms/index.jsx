import * as React from "react";
import {
  Box,
  Button,
  Modal,
  Typography,
  TextField,
  Autocomplete,
  Container,
} from "@mui/material";
import RoomsTable from "../../Components/Tables/RoomsTable";

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
  marginTop: "2%",
  borderRadius: 20,
  backgroundColor: "#00939F",
};

export default function Rooms() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return(
    <React.Fragment>
      <Container maxWidth="lg">
      <Button
          variant="contained"
          style={buttonStyle}
          onClick={handleOpen}
          disableRipple
        >
          Cadastrar Sala
        </Button>
        <RoomsTable />
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
              Nova Sala <br />
            </Typography>
            <TextField
              required
              type="text"
              id="outlined-required"
              label="Clinica"
              sx={{ marginTop: "1.5rem" }}
            />
            <TextField
              required
              type="room"
              id="outlined-required"
              label="Identificação"
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
            <TextField
              required
              type="ocupMax"
              id="outlined-required"
              label="Ocupação Max."
              sx={{ marginTop: "1.5rem" }}
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