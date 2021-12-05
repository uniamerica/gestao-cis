import React, {useEffect} from "react";
import { TextField, Button, Container, Box, Typography, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, Modal} from "@mui/material";
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
  maxHeight: '80vh',
  borderRadius: 1,
  boxShadow: 24,
  p: 4,
  overflow: 'scroll',
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

function createData(id, name, email, confirm, edit, del) {
  return {id, name, email, confirm, edit, del };
}

const rows = [
  createData("bc0fe7b4-cb3d-42e8-8ed1-d9b8e1c45ff4", "fabiofrassonsexy", "fabiosexy@mail.com", "Confirmar", 'Editar', "Deletar"),  
];

export default function Patients() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return(
    <React.Fragment>
      <Container maxWidth="lg">
        <Box sx={{marginTop: '4rem', display: 'flex', flexDirection: 'column', gap: '2rem'}}>
          <Box sx={{display: 'flex', alignItems: "center", justifyContent: 'space-between'}}>
            <Typography variant="h5" fontWeight="bold">
                Pacientes Registrados
            </Typography>
            <Button variant="contained" sx={{backgroundColor: "#00939F", borderRadius: 12}} onClick={handleOpen}>
              Novo Paciente
            </Button>
          </Box>
          <TableContainer component={Paper}>
            <Table aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell align="center">Id</StyledTableCell>
                  <StyledTableCell align="center">Nome</StyledTableCell>
                  <StyledTableCell align="center">Email</StyledTableCell>
                  <StyledTableCell align="center">Ações</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.id}>
                    <StyledTableCell align="center" component="th" scope="row">{row.id}</StyledTableCell>
                    <StyledTableCell align="center">{row.name}</StyledTableCell>
                    <StyledTableCell align="center">{row.email}</StyledTableCell>
                    <StyledTableCell align="center" sx={{display:'flex', gap: '.5rem', justifyContent: 'center'}}>
                      <Button variant="contained" size="small" color="success" sx={{ backgroundColor: '#00a887', textAlign: 'center' }} startIcon={<CheckIcon />}>
                        {row.confirm}
                      </Button>
                      <Button variant="contained" size="small" color="warning" startIcon={<EditIcon />}>
                        {row.edit}
                        </Button>
                      <Button variant="contained" size="small" color="error" startIcon={<DeleteIcon />}>
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

      <Modal disableBackdropClick open={open} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Cadastro de novo paciente
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome Completo"/>
                <div style={{display: "flex", alignItems: "center", justifyContent: "space-between", }}>
                  <TextField required type="date" id="outlined-required"  label="Data de Nascimento" value={"2000-01-01"}/>
                  <FormControl component="fieldset">
                    <FormLabel component="legend">Gênero</FormLabel>
                    <RadioGroup>
                      <FormControlLabel value="M" control={<Radio />} label="Masculino"/>
                      <FormControlLabel value="F"  control={<Radio />} label="Feminino"/>
                    </RadioGroup>
                  </FormControl>
                </div>
                <TextField required type="text" id="outlined-required" label="CPF"/>
                <TextField required type="text" id="outlined-required" label="RG"/>
                <TextField required type="email" id="outlined-required" label="Email"/>
                <TextField required type="text" id="outlined-required" label="Telefone"/>
                <TextField required type="text" id="outlined-required" label="Rua" />
                <TextField required type="text" id="outlined-required" label="Cidade" />
                <TextField required type="text" id="outlined-required" label="Bairro" />
                <TextField required type="text" id="outlined-required" label="País" />
                <TextField required type="text" id="outlined-required" label="UF" />
                <TextField required type="text" id="outlined-required" label="Número" />
                <TextField required type="text" id="outlined-required" label="CEP"/>
          <Button type="submit" variant="contained" color="success" sx={{backgroundColor: "#00939F", '&:hover': {backgroundColor: "#006870"} }}>
            Cadastrar
          </Button>
          <Button type="reset" variant="contained" onClick={handleClose} sx={{backgroundColor: "#c3c3c3" }}>
            Cancelar
          </Button>
        </Box>
      </Modal>
    </React.Fragment>
  );
}