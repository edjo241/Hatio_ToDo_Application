import React from 'react';
import '../styles/TodoFilter.css'

const TodoFilter = ({ handleFilterChange }) => {
  return (
    <div className="todo-filter">
      <h3>Filter Options</h3>
      <label>
        <input type="checkbox" name="completed" onChange={handleFilterChange} />
        Show Completed Todos
      </label>
      <label>
        <input type="checkbox" name="incomplete" onChange={handleFilterChange} />
        Show Incomplete Todos
      </label>
    </div>
  );
};

export default TodoFilter;
