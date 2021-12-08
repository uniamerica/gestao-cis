import React, { useEffect } from "react";
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
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import CheckIcon from "@mui/icons-material/Check";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import AuthError from "./../AuthError/index";

const modalStyle = {
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  position: "absolute",
  left: "50%",
  top: "50%",
  display: "flex",
  flexDirection: "column",
  gap: "1rem",
  width: 400,
  borderRadius: 1,
  boxShadow: 24,
  p: 4,
};

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#1CA78C",
    color: theme.palette.common.white,
    fontSize: 18,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

function createData(name, room, time, date, confirm, edit, del) {
  return { name, room, time, date, confirm, edit, del };
}

const rows = [
  createData(
    "fabiofrassonsexy",
    "69",
    "14:20",
    "01/01/2023",
    "Confirmar",
    "Editar",
    "Deletar"
  ),
];

export default function Home() {
  const navigate = useNavigate();

  // Auth Validation
  const { tokenExists, user } = React.useContext(AuthContext);
  const [openSave, createStatus] = React.useState(false);
  const openCreate = () => createStatus(true);
  const closeCreate = () => createStatus(false);
  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  const [hour, setHour] = React.useState("");
  const handleHour = (event) => {
    setHour(event.target.value);
  };
  const handleOpen = (event) => {
    setHour(event.target.value);
  };

  if (!tokenExists) {
    return (
      <>
        <AuthError />
      </>
    );
  }

  return (
    <React.Fragment>
      <Container maxWidth="lg">
        <Box
          sx={{
            marginTop: "4rem",
            display: "flex",
            flexDirection: "column",
            gap: "2rem",
          }}
        >
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            <Typography variant="h5" fontWeight="bold">
              Próximas Consultas
            </Typography>
            <Button
              variant="contained"
              sx={{ backgroundColor: "#00939F", borderRadius: 12 }}
              onClick={openCreate}
            >
              Nova Consulta
            </Button>
          </Box>
          <TableContainer component={Paper}>
            <Table aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell align="center">Nome</StyledTableCell>
                  <StyledTableCell align="center">Sala</StyledTableCell>
                  <StyledTableCell align="center">Data</StyledTableCell>
                  <StyledTableCell align="center">Horário</StyledTableCell>
                  <StyledTableCell align="center">Ações</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.id}>
                    <StyledTableCell align="center" component="th" scope="row">
                      {row.name}
                    </StyledTableCell>
                    <StyledTableCell align="center">{row.room}</StyledTableCell>
                    <StyledTableCell align="center">{row.date}</StyledTableCell>
                    <StyledTableCell align="center">{row.time}</StyledTableCell>
                    <StyledTableCell
                      align="center"
                      sx={{
                        display: "flex",
                        gap: ".5rem",
                        justifyContent: "center",
                      }}
                    >
                      {!!user.role && user.role !== "ROLE_PATIENT" && (
                        <Button
                          variant="contained"
                          size="small"
                          color="success"
                          sx={{
                            backgroundColor: "#00a887",
                            textAlign: "center",
                            boxShadow: "none",
                          }}
                          startIcon={<CheckIcon />}
                        >
                          {row.confirm}
                        </Button>
                      )}

                      <Button
                        variant="contained"
                        size="small"
                        color="warning"
                        sx={{ boxShadow: "none" }}
                        onClick={openEdit}
                        startIcon={<EditIcon />}
                      >
                        {row.edit}
                      </Button>
                      <Button
                        variant="contained"
                        size="small"
                        color="error"
                        sx={{ boxShadow: "none" }}
                        startIcon={<DeleteIcon />}
                      >
                        {row.del}
                      </Button>
                    </StyledTableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Box>
      </Container>

      <Modal
        disableBackdropClick
        open={openSave}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Solicitação de Agendamento <br />
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome" />
          <TextField required type="date" id="outlined-required" />
          <Autocomplete
            required
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
          <Box sx={{ display: "flex" }}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Horário</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={hour}
                label="Horário"
                onChange={handleHour}
              >
                <MenuItem value={1}>08:00</MenuItem>
                <MenuItem value={2}>08:30</MenuItem>
                <MenuItem value={3}>09:00</MenuItem>
                <MenuItem value={4}>09:30</MenuItem>
                <MenuItem value={5}>10:00</MenuItem>
                <MenuItem value={6}>10:30</MenuItem>
                <MenuItem value={7}>11:00</MenuItem>
                <MenuItem value={8}>11:30</MenuItem>
                <MenuItem value={9}>12:00</MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Button
            type="submit"
            variant="contained"
            color="success"
            sx={{
              backgroundColor: "#00939F",
              "&:hover": { backgroundColor: "#006870" },
            }}
          >
            Solicitar
          </Button>
          <Button
            type="reset"
            variant="contained"
            onClick={closeCreate}
            sx={{ backgroundColor: "#c3c3c3" }}
          >
            Cancelar
          </Button>
        </Box>
      </Modal>

      <Modal
        disableBackdropClick
        open={openModify}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Solicitação de Agendamento <br />
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome" />
          <TextField required type="date" id="outlined-required" />
          <Autocomplete
            required
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
          <Box sx={{ display: "flex" }}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Horário</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={hour}
                label="Horário"
                onChange={handleHour}
              >
                <MenuItem value={1}>08:00</MenuItem>
                <MenuItem value={2}>08:30</MenuItem>
                <MenuItem value={3}>09:00</MenuItem>
                <MenuItem value={4}>09:30</MenuItem>
                <MenuItem value={5}>10:00</MenuItem>
                <MenuItem value={6}>10:30</MenuItem>
                <MenuItem value={7}>11:00</MenuItem>
                <MenuItem value={8}>11:30</MenuItem>
                <MenuItem value={9}>12:00</MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Button
            type="submit"
            variant="contained"
            color="success"
            sx={{
              backgroundColor: "#00939F",
              "&:hover": { backgroundColor: "#006870" },
            }}
          >
            Solicitar
          </Button>
          <Button
            type="reset"
            variant="contained"
            onClick={closeCreate}
            sx={{ backgroundColor: "#c3c3c3" }}
          >
            Cancelar
          </Button>
        </Box>
      </Modal>

      <Modal
        disableBackdropClick
        open={openModify}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Alteração de Agendamento <br />
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome" />
          <TextField required type="date" id="outlined-required" />
          <Autocomplete
            required
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
          <Box sx={{ display: "flex" }}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Horário</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={hour}
                label="Horário"
                onChange={handleHour}
              >
                <MenuItem value={1}>08:00</MenuItem>
                <MenuItem value={2}>08:30</MenuItem>
                <MenuItem value={3}>09:00</MenuItem>
                <MenuItem value={4}>09:30</MenuItem>
                <MenuItem value={5}>10:00</MenuItem>
                <MenuItem value={6}>10:30</MenuItem>
                <MenuItem value={7}>11:00</MenuItem>
                <MenuItem value={8}>11:30</MenuItem>
                <MenuItem value={9}>12:00</MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Button
            type="submit"
            variant="contained"
            color="success"
            sx={{
              backgroundColor: "#00939F",
              "&:hover": { backgroundColor: "#006870" },
            }}
          >
            Solicitar alteração
          </Button>
          <Button
            type="reset"
            variant="contained"
            onClick={closeEdit}
            sx={{ backgroundColor: "#c3c3c3" }}
          >
            Cancelar
          </Button>
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
