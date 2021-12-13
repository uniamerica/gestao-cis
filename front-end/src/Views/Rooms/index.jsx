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
import { useForm } from "react-hook-form";

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

export default function Rooms() {
  const [specialtiesList, setSpecialties] = useState([]); 
  const { register, handleSubmit } = useForm();
  const onSubmit = handleSubmit((sala) =>  {
    axios.post("http://localhost:8080/api/rooms", sala).then(function (response) {
      if(response.status === 201) {
        alert("Sala criada com sucesso");
      }
    }
  )})

  // Create
  const [openSave, createStatus] = React.useState(false); 
  const openCreate = () => createStatus(true);
  const closeCreate = () => createStatus(false);

  // Edit
  const [openModify, editStatus] = React.useState(false);
  const openEdit = () => editStatus(true);
  const closeEdit = () => editStatus(false);

  // Get Rows
  const [rows, setRows] = useState([]);

  const [sala] = useState({});

  // GET
  useEffect(() => { 
    axios.get("http://localhost:8080/api/specialties").then(function (response) {
      const data = response.data;
      setSpecialties(data);
    }).then(() => {
      axios.get("http://localhost:8080/api/rooms").then(function (response) {
      const data = response.data;
      const dataRows = data.map((dataRow) => createData(dataRow.id, dataRow.roomNumber, 
        dataRow.specialties[0].name, 
        <ModifyModal specialtiesList={specialtiesList} room={dataRow} />, 
        "Deletar"))
      setRows(dataRows);
    });
    })
    
  }, []);

  // CREATE
  // const submit = axios.post("http://localhost:8080/api/rooms", sala).then(function (response) {
  //   if(response.status === 201) {
  //     alert("Sala criada com sucesso")
  //   }
  // })

  

  // DELETE
  const deleteRoom = (id) => {
    axios.delete("http://localhost:8080/api/rooms/" + id).then(function (response) {
      if(response.status === 204) {
        alert("Sala deletada com sucesso")
      }
    })
  }

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
                      <Button variant="contained" size="small" color="warning" sx={{boxShadow: "none"}} onClick={openEdit} startIcon={<EditIcon />}>
                        {row.edit}
                      </Button>
                      <Button variant="contained" size="small" color="error" sx={{boxShadow: "none"}} onClick={() => deleteRoom(row.id)} startIcon={<DeleteIcon />}>
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
        <Box component="form" sx={modalStyle} onSubmit={onSubmit}>
          <Typography variant="h5" color="initial">
            Cadastro de nova sala
          </Typography>
          <TextField required type="number" id="outlined-required" label="Número" {...register('roomNumber')} />
          <Autocomplete
            required
            multiple
            id="tags-outlined"
            options={specialtiesList}
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
    </React.Fragment>
  );
}
const ModifyModal = ({room, specialtiesList}) => {
  const [ open, setOpen ] = useState(false);
  const [number, setNumber] = useState(room.roomNumber);
  const [specialty, setSpecialty] = useState(room.specialties || []);

  // EDIT - DEIXAR ISSO PRA OUTRO MOMENTO
  const editRoom = (e) => {
    e.preventDefault();
    const toEdit = {
      roomNumber: number,
      specialties: specialty.map(s => s.name)
    }
    console.log(toEdit);
    return
    axios.put("http://localhost:8080/api/rooms").then(function (response) {
    if(response.status === 201) {
      alert("Sala editada com sucesso")
    }
  })
  }
  
  return (
    <>
    <Button onClick={() => setOpen(true)}>Editar</Button>
    <Modal  open={open} onClose={() => setOpen(false)} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
      <Box component="form" sx={modalStyle} onSubmit={(e) => editRoom(e)} >
        <Typography variant="h5" color="initial">
          Editar sala
        </Typography>
        <TextField required id="outlined-required" label="Número" defaultValue={room.roomNumber} onChange={(e) => setNumber(e.target.value)} />
        <Autocomplete 
          required
          multiple
          id="tags-outlined"
          value={specialty}
          options={specialtiesList}
          getOptionLabel={(option) => option.name}
          filterSelectedOptions
          onChange={(e, newValue) => setSpecialty(newValue)}
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
        <Button type="reset" variant="contained" onClick={() => setOpen(false)} sx={{backgroundColor: "#c3c3c3" }}>
          Cancelar
        </Button>
      </Box>
    </Modal>
    </>
  )
}