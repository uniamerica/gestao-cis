import React, {useEffect} from "react";
import { TextField, Button, Container, Box, Typography, Autocomplete, Modal} from "@mui/material";
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

function createData(id, number, specialty, edit, del ) {
  return {id, number, specialty, edit, del };
}

const rows = [
  createData("bc0fe7b4-cb3d-42e8-8ed1-d9b8e1c45ff4", 69, "Nutrição", "Editar", "Deletar"),  
];

export default function Rooms() {
  const [openSave, createStatus] = React.useState(false);
  const openCreate = () => createStatus(true);
  const closeCreate = () => createStatus(false);
  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  return(
    <React.Fragment>
      <Container maxWidth="lg">
        <Box sx={{marginTop: '4rem', display: 'flex', flexDirection: 'column', gap: '2rem'}}>
          <Box sx={{display: 'flex', alignItems: "center", justifyContent: 'space-between'}}>
            <Typography variant="h5" fontWeight="bold">
                Salas Registradas
            </Typography>
            <Button variant="contained" sx={{backgroundColor: "#00939F", borderRadius: 12, boxShadow: "none"}} onClick={openCreate}>
              Nova sala
            </Button>
          </Box>
          <TableContainer component={Paper}>
            <Table aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell align="center">Id</StyledTableCell>
                  <StyledTableCell align="center">Numero</StyledTableCell>
                  <StyledTableCell align="center">Especialidades</StyledTableCell>
                  <StyledTableCell align="center">Ações</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.id}>
                    <StyledTableCell align="center" component="th" scope="row">{row.id}</StyledTableCell>
                    <StyledTableCell align="center">{row.number}</StyledTableCell>
                    <StyledTableCell align="center">{row.specialty}</StyledTableCell>
                    <StyledTableCell align="center" sx={{display:'flex', gap: '.5rem', justifyContent: 'center'}}>
                      <Button variant="contained" size="small" color="success" sx={{ backgroundColor: '#00a887', textAlign: 'center', boxShadow: "none" }} startIcon={<CheckIcon />}>
                        {row.confirm}
                      </Button>
                      <Button variant="contained" size="small" color="warning" sx={{boxShadow: "none"}} onClick={openEdit} startIcon={<EditIcon />}>
                        {row.edit}
                        </Button>
                      <Button variant="contained" size="small" color="error" sx={{boxShadow: "none"}} startIcon={<DeleteIcon />}>
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

      <Modal disableBackdropClick open={openSave} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Cadastro de nova sala
          </Typography>
          <TextField required type="number" id="outlined-required" label="Número" />
          <Autocomplete
            required
            multiple
            id="tags-outlined"
            options={specialty}
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
          <Button type="submit" variant="contained" color="success" sx={{backgroundColor: "#00939F", '&:hover': {backgroundColor: "#006870"} }}>
            Cadastrar
          </Button>
          <Button type="reset" variant="contained" onClick={closeCreate} sx={{backgroundColor: "#c3c3c3" }}>
            Cancelar
          </Button>
        </Box>
      </Modal>
      <Modal disableBackdropClick open={openModify} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Editar sala
          </Typography>
          <TextField required type="number" id="outlined-required" label="Número" />
          <Autocomplete
            required
            multiple
            id="tags-outlined"
            options={specialty}
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
          <Button type="submit" variant="contained" color="success" sx={{backgroundColor: "#00939F", '&:hover': {backgroundColor: "#006870"} }}>
            Salvar
          </Button>
          <Button type="reset" variant="contained" onClick={closeEdit} sx={{backgroundColor: "#c3c3c3" }}>
            Cancelar
          </Button>
        </Box>
      </Modal>
    </React.Fragment>
  );
}

const specialty = [
  { id: 0, name: "Acupuntura" },
  { id: 1, name: "Psicologia" },
  { id: 2, name: "Nutrição" },
  { id: 3, name: "Educação Física" },
  { id: 4, name: "Fonoaudiologia" },
  { id: 5, name: "Farmácia" },
  { id: 6, name: "Medicina" },
  { id: 7, name: "Odontologia" },
];