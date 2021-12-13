import React, {useEffect, useState} from "react";
import { TextField, Button, Container, Box, Typography, Autocomplete, Modal} from "@mui/material";
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import axios from "axios";

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
  overflow: 'auto',
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

function createData(id, email, name, phone, specialty, professionalDocument, edit, del ) {
  return {id, email, name, phone, specialty, professionalDocument, edit, del };
}

export default function Professional() {
  const [openSave, createStatus] = React.useState(false);
  const openCreate = () => createStatus(true);
  const closeCreate = () => createStatus(false);
  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  const [rows, setRows] = useState([]);

  // GET
  useEffect(() => { 
    axios.get("http://localhost:8080/api/specialties").then(function (response) {
      const data = response.data;
      setSpecialties(data);
    }).then(() => {
      axios.get("http://localhost:8080/api/health-professionals").then(function (response) {
      const data = response.data;
      const dataRows = data.map((dataRow) => createData(dataRow.id, dataRow.email, dataRow.name, dataRow.phone, dataRow.specialty, dataRow.professionalDocument, "Editar", "Deletar"))
      setRows(dataRows);
      <ModifyModal professional={dataRows} specialtiesList={specialtiesList} />;
    });
    })
  }, []);

  const [specialtiesList, setSpecialties] = useState([]);
  const [ open, setOpen ] = useState(false);
  const [professionalName, setprofessionalName] = useState('');
  const [professionalPassword, setprofessionalPassword] = useState('');
  const [professionalEmail, setprofessionalEmail] = useState('');
  const [professionalPhone, setprofessionalPhone] = useState('');
  const [professionalDocument, setprofessionalDocument] = useState('');
  const [professionalSpecialty, setProfessionalSpecialty] = useState('' || []);

  // CREATE
  const saveProfessional = (e) => {
    e.preventDefault();
    const toSave = {
      name: professionalName,
      password: professionalPassword,
      email: professionalEmail,
      phone: professionalPhone,
      professionalDocument: professionalDocument,
      specialtyId: professionalSpecialty[0].id
    }
    axios.post(`http://localhost:8080/api/health-professionals`, toSave).then(function (response) {
    if(response.status === 201) {
      alert("Profissional Cadastrado");
      setOpen(false);
    } 
  })
  }

  // DELETE
  const deleteProfessional = (id) => {
    axios.delete(`http://localhost:8080/api/health-professionals/${id}`).then(function (response) {
      if(response.status === 204) {
        alert("Profissional deletado!")
      }
    })
  }

  // EDIT
  const editProfessional = (e, id) => {
    e.preventDefault();
    const toEdit = {
      name: professionalName,
      email: professionalEmail,
      phone: professionalPhone,
      professionalDocument: professionalDocument,
      specialtyId: professionalSpecialty[0].id
    }
    axios.put(`http://localhost:8080/api/health-professionals/${id}`, toEdit).then(function (response) {
    if(response.status === 200) {
      alert("Profissional editado com sucesso");
      setOpen(false);
    } 
  })
  }

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
                    <StyledTableCell align="center">{row.professionalDocument}</StyledTableCell>
                    <StyledTableCell align="center">
                      <Box sx={{display: 'flex', gap: '1rem'}}>
                        <Button variant="contained" size="small" color="warning" onClick={openEdit} sx={{boxShadow: "none"}} startIcon={<EditIcon />}>
                          {row.edit}
                          </Button>
                        <Button variant="contained" size="small" color="error" sx={{boxShadow: "none"}} onClick={() => window.confirm("Deseja deletar o profissional?") ? deleteProfessional(row.id) : ""} startIcon={<DeleteIcon />}>
                          {row.del}
                        </Button>
                      </Box>
                    </StyledTableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Box>
      </Container>

      <Modal open={openSave} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle} onSubmit={(e) => saveProfessional(e)}>
          <Typography variant="h5" color="initial">
            Cadastro de novo profissional
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome Completo" onChange={(e) => setprofessionalName(e.target.value)}/>
          <TextField required type="mail" id="outlined-required" label="Email" onChange={(e) => setprofessionalEmail(e.target.value)}/>
          <TextField required type="password" id="outlined-required" label="Senha" onChange={(e) => setprofessionalPassword(e.target.value)}/>
          <TextField required type="text" id="outlined-required" label="Telefone" onChange={(e) => setprofessionalPhone(e.target.value)} />
          <TextField required type="text" id="outlined-required" label="CRM" onChange={(e) => setprofessionalDocument(e.target.value)} />
          <Autocomplete 
            required
            multiple
            id="tags-outlined"
            options={specialtiesList}
            getOptionLabel={(option) => option.name}
            filterSelectedOptions
            onChange={(e, newValue) => setProfessionalSpecialty(newValue)}
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

      {/* <Modal open={openModify} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box component="form" sx={modalStyle} onSubmit={(e) => editProfessional(e)}>
          <Typography variant="h5" color="initial">
            Editar profissional
          </Typography>
          <TextField required type="text" id="outlined-required" label="Nome Completo" onChange={(e) => setprofessionalName(e.target.value)}/>
          <TextField required type="mail" id="outlined-required" label="Email" onChange={(e) => setprofessionalEmail(e.target.value)}/>
          <TextField required type="text" id="outlined-required" label="Telefone" onChange={(e) => setprofessionalPhone(e.target.value)} />
          <TextField required type="text" id="outlined-required" label="CRM" onChange={(e) => setprofessionalDocument(e.target.value)} />
          <Autocomplete 
            required
            multiple
            id="tags-outlined"
            options={specialtiesList}
            getOptionLabel={(option) => option.name}
            filterSelectedOptions
            onChange={(e, newValue) => setProfessionalSpecialty(newValue)}
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
      </Modal> */}
    </React.Fragment>
  );
}

const ModifyModal = ({professional, specialtiesList}) => {
  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  const [ open, setOpen ] = useState(false);
  const [professionalName, setprofessionalName] = useState('');
  const [professionalEmail, setprofessionalEmail] = useState('');
  const [professionalPhone, setprofessionalPhone] = useState('');
  const [professionalDocument, setprofessionalDocument] = useState('');
  const [professionalSpecialty, setProfessionalSpecialty] = useState('' || []);

  // EDIT
  const editProfessional = (e) => {
    e.preventDefault();
    const toEdit = {
      name: professionalName,
      email: professionalEmail,
      phone: professionalPhone,
      professionalDocument: professionalDocument,
      specialtyId: professionalSpecialty[0].id
    }
    axios.put(`http://localhost:8080/api/health-professionals/${professional.id}`, toEdit).then(function (response) {
    if(response.status === 200) {
      alert("Sala editada com sucesso");
      setOpen(false);
    } 
  })
  }
  
  return (
    <>
    <Button onClick={() => setOpen(true)}>Editar</Button>
    <Modal open={open} onClose={() => setOpen(false)} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
      <Box component="form" sx={modalStyle} onSubmit={(e) => editProfessional(e)}>
        <Typography variant="h5" color="initial">
          Editar profissional
        </Typography>
        <TextField required type="text" id="outlined-required" label="Nome Completo" onChange={(e) => setprofessionalName(e.target.value)}/>
        <TextField required type="mail" id="outlined-required" label="Email" onChange={(e) => setprofessionalEmail(e.target.value)}/>
        <TextField required type="text" id="outlined-required" label="Telefone" onChange={(e) => setprofessionalPhone(e.target.value)} />
        <TextField required type="text" id="outlined-required" label="CRM" onChange={(e) => setprofessionalDocument(e.target.value)} />
        <Autocomplete 
          required
          multiple
          id="tags-outlined"
          options={specialtiesList}
          getOptionLabel={(option) => option.name}
          filterSelectedOptions
          onChange={(e, newValue) => setProfessionalSpecialty(newValue)}
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
    </>
  )
}