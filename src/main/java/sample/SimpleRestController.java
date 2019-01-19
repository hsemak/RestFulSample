package sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.Exception.RecordNotFoundException;
import sample.model.EmployeeVO;

@RestController
public class SimpleRestController {
    @GetMapping("/example")
    public String example() {
        return "Hello User !! " + new Date();
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<EmployeeVO> getEmployeeById (@PathVariable("id") int id)
    {
        EmployeeVO employee = null;
       if (id == 1) {
           employee =
                   new EmployeeVO(new Integer(1), "Kamesh","Chandrakumar","ckamesh06@gmail.com");
       }


        if(employee == null) {
            throw new RecordNotFoundException("Invalid employee id : " + id);
        }
        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
    }
    @GetMapping(value = "/employees/name")
    public ResponseEntity<List<EmployeeVO>> getEmployeeByName (@RequestParam("name") String name)
    {
        List<EmployeeVO> employee = null;
        if (name.equals("Kamesh") ) {
            employee = new ArrayList<>();
            EmployeeVO employeeObject =
                    new EmployeeVO(new Integer(1), "Kamesh","Chandrakumar","ckamesh06@gmail.com");
        employee.add(employeeObject);
        }

        if(employee == null) {
            throw new RecordNotFoundException("Invalid employee name : " + name);
        }
        return new ResponseEntity<List<EmployeeVO>>(employee, HttpStatus.OK);
    }

}
