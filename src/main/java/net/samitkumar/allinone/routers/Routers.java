package net.samitkumar.allinone.routers;

import lombok.extern.slf4j.Slf4j;
import net.samitkumar.allinone.handlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@Slf4j
public class Routers {
    @Bean
    RouterFunction<ServerResponse> routerFunction(FileHandler fileHandler,
                                                  JobTitleHandler jobTitleHandler,
                                                  DepartmentHandler departmentHandler,
                                                  EmployeeHandler employeeHandler,
                                                  EmployeeDocumentsHandler employeeDocumentsHandler,
                                                  EmployeeHistoryHandler employeeHistoryHandler) {
        return RouterFunctions
                .route()
                .path("/scan", builder -> builder
                        .GET("", fileHandler::scan)
                )
                .path("/db", builder -> builder
                        .path("/department", deptBuilder -> deptBuilder
                                .GET("", departmentHandler::allDepartment)
                                .GET("/{id}", departmentHandler::departmentById)
                                .POST("", departmentHandler::newDepartment)
                                .PUT("/{id}", departmentHandler::updateDepartment)
                                .DELETE("/{id}", departmentHandler::deleteDepartment))
                        .path("/jobtitle", jtbuilder -> jtbuilder
                                .GET("", jobTitleHandler::allJobTitle)
                                .GET("/{id}", jobTitleHandler::jobTitleById)
                                .POST("", jobTitleHandler::newJobTitle)
                                .PUT("/{id}", jobTitleHandler::updateJobTitle)
                                .DELETE("/{id}", jobTitleHandler::deleteJobTitle))
                        .path("/employee", empBuilder -> empBuilder
                                .path("/documents", documentbuilder -> documentbuilder
                                        .GET("", employeeDocumentsHandler::allEmployeeDocuments)
                                        .GET("/{id}", employeeDocumentsHandler::employeeDocumentsbyId)
                                        .POST("", employeeDocumentsHandler::newEmployeeDocument)
                                        .PUT("/{id}", employeeDocumentsHandler::updateEmployeeDocument)
                                        .DELETE("/{id}", employeeDocumentsHandler::deleteEmployeeDocument)
                                )
                                .path("/history", historyBuilder -> historyBuilder
                                        .GET("", employeeHistoryHandler::allEmployeeHistory)
                                        .GET("/{id}", employeeHistoryHandler::employeeHistoryById)
                                        .POST("", employeeHistoryHandler::newEmployeeHistory)
                                        .PUT("/{id}", employeeHistoryHandler::updateEmployeeHistory)
                                        .DELETE("/{id}", employeeHistoryHandler::deleteEmployeeHistory)
                                )
                                .GET("", employeeHandler::allEmployee)
                                .GET("/{id}", employeeHandler::employeeById)
                                .POST("", employeeHandler::newEmployee)
                                .POST("/multipart", employeeHandler::multipartNewEmployee)
                                .PUT("/{id}", employeeHandler::updateEmployee)
                                .DELETE("/{id}", employeeHandler::deleteEmployee)
                        )
                )
                .path("/file", fileBuilder -> fileBuilder
                        .POST("/upload", fileHandler::fileUpload)
                        .GET("/download/{id}", fileHandler::fileDownload)
                        .GET("/view/{id}", fileHandler::viewFile)
                        .GET("/all", fileHandler::allFiles)
                        //TODO implement DELETE
                )
                .after((request, response) -> {
                    log.info("{} {} {}", request.method(), request.path(), response.statusCode());
                    return response;
                })
                .build();
    }
}
