package com.tanirbergen.FirstProjectRestApi.Controllers;

import com.tanirbergen.FirstProjectRestApi.Dto.PersonDto;
import com.tanirbergen.FirstProjectRestApi.Model.Person;
import com.tanirbergen.FirstProjectRestApi.Service.PersonService;
import com.tanirbergen.FirstProjectRestApi.Util.PersonNotFoundValidator;
import com.tanirbergen.FirstProjectRestApi.Util.PersonResponse;
import com.tanirbergen.FirstProjectRestApi.Util.PersonResponseNotFoundId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PersonRestController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonRestController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDto> getPerson() {
        return personService.findAll().stream().map(this::convertToPersonDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDto getIndex(@PathVariable("id") int id) {
        return convertToPersonDto(personService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDto personDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder str = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                str.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotFoundValidator(str.toString());
        }
        personService.save(convertToDto(personDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<PersonResponse> response(PersonResponseNotFoundId p) {
        PersonResponse personResponse = new PersonResponse("this is id not found",
                System.currentTimeMillis());
        return new ResponseEntity<>(personResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PersonResponse> response(PersonNotFoundValidator p) {
        PersonResponse personResponse = new PersonResponse(p.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(personResponse, HttpStatus.BAD_REQUEST);
    }

    private Person convertToDto(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    private PersonDto convertToPersonDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }
}
