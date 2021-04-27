import React, { useState } from 'react';
import ProductService from '../services/ProductService';
import AsyncSelect from 'react-select/async';
 
function DropdownInput(props) {
  const [,setValue] = useState('');
  const [selectedValue,] = useState(null);
 
  // handle input change event
  const handleInputChange = value => {
    setValue(value);
  };
 
  // handle selection
  const handleChange = value => {
    props.onAddProduct(value.product);
    setValue(null);
  }
 
  // load options using API call
  const loadOptions = input => ProductService.getProducts(input);
  
 
  return (
    <div className="DropdownInput">
      <AsyncSelect
        value={selectedValue}
        // getOptionLabel={e => e.title}
        // getOptionValue={e => e.id}
        loadOptions={loadOptions}
        onInputChange={handleInputChange}
        onChange={handleChange}
      />
      <pre>{selectedValue}</pre>
    </div>
  );
}
 
export default DropdownInput;