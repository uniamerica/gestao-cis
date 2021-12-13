import React from "react";
import { Box,Button,Modal,Typography,TextField,Autocomplete,Container } from "@mui/material";
import { AuthContext } from "./../../Contexts/authContext";
import { useNavigate } from "react-router";
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import CheckIcon from '@mui/icons-material/Check';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AuthError from './../AuthError/index';

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

function createData(room, date, hour, minute, edit, del) {
  return { room, date, hour, minute, edit, del };
}

const rows = [
  createData( 1, "01/01/2023", "14", "30" , "Editar", "Deletar")
];


export default function Schedule() {

  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  const [hour, setHour] = React.useState('');
  const [minute, setMinute] = React.useState('');
  const [room, setRoom] = React.useState('');

  const handleHour = (event) => {
    setHour(event.target.value);
  };

  const handleMinute = (event) => {
    setMinute(event.target.value);
  };

  const handleRoom = (event) => {
    setRoom(event.target.value);
  };

  const { isAuth } = React.useContext(AuthContext);
  if (!isAuth) {
    return (
      <React.Fragment>
        <AuthError />
      </React.Fragment>
    )
  } else {
    return (
      <React.Fragment>
        <Container maxWidth="lg">
          <Box sx={{ marginTop: '4rem', display: 'flex', flexDirection: 'column', gap: '2rem' }}>
            <Box sx={{ display: 'flex', alignItems: "center", justifyContent: 'space-between' }}>
              <Typography variant="h5" fontWeight="bold">
                Horários Disponíveis
              </Typography>
              <Button variant="contained" onClick={openEdit} sx={{ backgroundColor: "#00939F", borderRadius: 12 }} >
                Novo Horário
              </Button>
            </Box>
            <TableContainer component={Paper}>
              <Table aria-label="customized table">
                <TableHead>
                  <TableRow>
                    <StyledTableCell align="center">Sala</StyledTableCell>
                    <StyledTableCell align="center">Data</StyledTableCell>
                    <StyledTableCell align="center">Horário</StyledTableCell>
                    <StyledTableCell align="center">Ações</StyledTableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow key={row.id}>
                      <StyledTableCell align="center" component="th" scope="row">{row.room}</StyledTableCell>
                      <StyledTableCell align="center">{row.date}</StyledTableCell>
                      <StyledTableCell align="center">{row.hour + ':' + row.minute}</StyledTableCell>
                      <StyledTableCell align="center" sx={{ display: 'flex', gap: '.5rem', justifyContent: 'center' }}>
                        <Button variant="contained" size="small" color="warning" sx={{ boxShadow: "none" }} startIcon={<EditIcon />}>
                          {row.edit}
                        </Button>
                        <Button variant="contained" size="small" color="error" sx={{ boxShadow: "none" }} startIcon={<DeleteIcon />}>
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
        <Modal  open={openModify} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Novo Horário Dispoível
          </Typography>
          <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Sala</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={room}
                label="Hora"
                onChange={handleRoom}
              >
                <MenuItem value={0}>1</MenuItem>
                <MenuItem value={1}>2</MenuItem>
                <MenuItem value={2}>3</MenuItem>
              </Select>
            </FormControl>
          <TextField required type="date" id="outlined-required" />
          <Box sx={{display: 'flex'}}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Hora</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={hour}
                label="Hora"
                onChange={handleHour}
              >
                <MenuItem value={0}>08</MenuItem>
                <MenuItem value={1}>09</MenuItem>
                <MenuItem value={2}>10</MenuItem>
                <MenuItem value={3}>11</MenuItem>
                <MenuItem value={4}>12</MenuItem>
              </Select>
            </FormControl>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Minuto</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={minute}
                label="Minuto"
                onChange={handleMinute}
              >
                <MenuItem value={0}>00</MenuItem>
                <MenuItem value={1}>30</MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Button type="submit" variant="contained" color="success" sx={{backgroundColor: "#00939F", '&:hover': {backgroundColor: "#006870"} }}>
            Solicitar
          </Button>
          <Button type="reset" variant="contained" onClick={closeEdit} sx={{backgroundColor: "#c3c3c3" }}>
            Cancelar
          </Button>
        </Box>
        </Modal>
      </React.Fragment>
    );
  }
}