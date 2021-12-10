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

function createData(id, email, name, phone, crm, edit, del ) {
  return {id, email, name, phone, crm, edit, del };
}

const rows = [
  createData("bc0fe7b4-cb3d-42e8-8ed1-d9b8e1c45ff4", "fabiosexy@mail.com", "fabiofrassonsexy", "(45)9345678", "111111", "Editar", "Deletar"),  
];

export default function Professional() {
  const [openSave, createStatus] = React.useState(false);
  const openCreate = () => createStatus(true);
  const closeCreate = () => createStatus(false);
  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  return (
    <React.Fragment>
      <Container maxWidth="lg">
        <Box sx={{marginTop: '4rem', display: 'flex', flexDirection: 'column', gap: '2rem'}}>
          <Box sx={{display: 'flex', alignItems: "center", justifyContent: 'space-between'}}>
            <Typography variant="h5" fontWeight="bold">
                Profissionais Registrados
            </Typography>
            <Button variant="contained" sx={{backgroundColor: "#00939F", borderRadius: 12, boxShadow: "none"}} onClick={openCreate}>
              Novo Profissional
            </Button>
          </Box>
          <TableContainer component={Paper}>
            <Table aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell align="center">Id</StyledTableCell>
                  <StyledTableCell align="center">Email</StyledTableCell>
                  <StyledTableCell align="center">Nome</StyledTableCell>
                  <StyledTableCell align="center">Telefone</StyledTableCell>
                  <StyledTableCell align="center">CRM</StyledTableCell>
                  <StyledTableCell align="center">Ações</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.id}>
                    <StyledTableCell align="center" component="th" scope="row">{row.id}</StyledTableCell>
                    <StyledTableCell align="center">{row.email}</StyledTableCell>
                    <StyledTableCell align="center">{row.name}</StyledTableCell>
                    <StyledTableCell align="center">{row.phone}</StyledTableCell>
                    <StyledTableCell align="center">{row.crm}</StyledTableCell>
                    <StyledTableCell align="center" sx={{display:'flex', gap: '.5rem', justifyContent: 'center'}}>
                      <Button variant="contained" size="small" color="warning" onClick={openEdit} sx={{boxShadow: "none"}} startIcon={<EditIcon />}>
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

      <Modal  open={openSave} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Cadastro de novo profissional
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome Completo"/>
          <TextField required type="text" id="outlined-required" label="Senha"/>
          <TextField required type="mail" id="outlined-required" label="Email"/>
          <TextField required type="text" id="outlined-required" label="Telefone" />
          <TextField required type="text" id="outlined-required" label="CRM" />
          <Autocomplete
            required
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
          <Button type="submit" variant="contained" color="success" sx={{backgroundColor: "#00939F", '&:hover': {backgroundColor: "#006870"} }}>
            Cadastrar
          </Button>
          <Button type="reset" variant="contained" onClick={closeCreate} sx={{backgroundColor: "#c3c3c3" }}>
            Cancelar
          </Button>
        </Box>
      </Modal>
      <Modal  open={openModify} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle}>
          <Typography variant="h5" color="initial">
            Editar profissional
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome Completo"/>
          <TextField required type="text" id="outlined-required" label="Senha"/>
          <TextField required type="mail" id="outlined-required" label="Email"/>
          <TextField required type="text" id="outlined-required" label="Telefone" />
          <TextField required type="text" id="outlined-required" label="CRM" />
          <Autocomplete
            required
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
