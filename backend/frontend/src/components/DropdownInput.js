import React, { useState } from 'react';
import ProductService from '../services/ProductService';
import AsyncSelect from 'react-select/async';
 
function DropdownInput() {
  const [inputValue, setValue] = useState('');
  const [selectedValue, setSelectedValue] = useState(null);
 
  // handle input change event
  const handleInputChange = value => {
    setValue(value);
  };
 
  // handle selection
  const handleChange = value => {
    setSelectedValue(value);
  }
 
  // load options using API call
  const loadOptions = input => ProductService.getProducts(input);
  
 
  return (
    <div className="DropdownInput">
      <AsyncSelect
        value={selectedValue}
        getOptionLabel={e => e.title}
        getOptionValue={e => e.id}
        loadOptions={loadOptions}
        onInputChange={handleInputChange}
        onChange={handleChange}
      />
      <pre>{selectedValue}</pre>
    </div>
  );
}
 
export default DropdownInput;