package io.spring.schedule.controller;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class JobLaunchingController {
    @Autowired
    private JobOperator jobOperator;

    @GetMapping(value="/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public long launch(@RequestParam("name") String name) throws Exception {
        return this.jobOperator.start("job", String.format("name=%s", name));
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void stop(@PathVariable("id") Long id) throws Exception {
        this.jobOperator.stop(id);
    }

    @PatchMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long restart(@PathVariable("id") Long id) throws Exception {
        return this.jobOperator.restart(id);
    }

}
