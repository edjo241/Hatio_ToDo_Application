import React from 'react';
import {BrowserRouter as Router,Route,Routes} from 'react-router-dom'
import LoginPage from './components/LoginPage';
import ProjectsPage from './components/ProjectsPage';
import TodoListPage from './components/TodoPage';
import { TodoWrapper } from './components/TodoWrapper';

function App() {
  return (
    <div className='container'>
      <Router>
        <Routes>
        
        <Route exact path='/' element={<LoginPage/>}/>
        <Route exact path='/projects' element={<ProjectsPage/>}/>
        <Route exact path='/projects/todo/:id' element={<TodoWrapper/>}/>
        {/* <Route path='/login' element={<Login/>}/>
        <Route  path='/home' element={<Home2/>}/>
        <Route  path='/details/:id' element={<Details/>}/>
        <Route  path='/addDetails/:id' element={<AddDetails/>}/>
        <Route  path='/patientDetails/:id' element={<PatientDetails/>}/> */}

        </Routes>
      </Router>
    </div>
    
   
  );
}

export default App;
