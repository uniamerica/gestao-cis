import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

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

function createData(id, username, name, email, cpf, rg, biologicalSex, birthdate, motherName, phone, role) {
  return { id, username, name, email, cpf, rg, biologicalSex, birthdate, motherName, phone, role };
}

const rows = [
  createData('49faaa3d-9933-4d6f-a7d6-d737dd5cf74d', "fabiofrassonsexy", "fabiofrasson", "fabiofrasson@balabla", "11111111111", "000000000", "M", "04/05/1989", "Saoserina", "45999439105", "Paciente"),  

];

export default function CustomizedTables() {
  return (
    <TableContainer component={Paper} sx={{ margin:"2rem 0"}}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Id</StyledTableCell>
            <StyledTableCell align="center">Username</StyledTableCell>
            <StyledTableCell align="center">Nome</StyledTableCell>
            <StyledTableCell align="center">E-mail</StyledTableCell>
            <StyledTableCell align="center">CPF</StyledTableCell>
            <StyledTableCell align="center">RG</StyledTableCell>
            <StyledTableCell align="center">Sexo Biologico</StyledTableCell>
            <StyledTableCell align="center">Nascimento</StyledTableCell>
            <StyledTableCell align="center">Nome da mãe</StyledTableCell>
            <StyledTableCell align="center">Numero</StyledTableCell>
            <StyledTableCell align="center">Cargo</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.id}>
              <StyledTableCell component="th" scope="row">
                {row.id}
              </StyledTableCell>
              <StyledTableCell align="center">{row.username}</StyledTableCell>
              <StyledTableCell align="center">{row.name}</StyledTableCell>
              <StyledTableCell align="center">{row.email}</StyledTableCell>
              <StyledTableCell align="center">{row.cpf}</StyledTableCell>
              <StyledTableCell align="center">{row.rg}</StyledTableCell>
              <StyledTableCell align="center">{row.biologicalSex}</StyledTableCell>
              <StyledTableCell align="center">{row.birthdate}</StyledTableCell>
              <StyledTableCell align="center">{row.motherName}</StyledTableCell>
              <StyledTableCell align="center">{row.phone}</StyledTableCell>
              <StyledTableCell align="center">{row.role}</StyledTableCell>

            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}