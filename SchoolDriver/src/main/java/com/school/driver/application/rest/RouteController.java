package com.school.driver.application.rest;

import com.school.driver.application.dto.request.*;
import com.school.driver.application.dto.response.*;
import com.school.driver.domain.mapper.RouteMapper;
import com.school.driver.domain.model.Route;
import com.school.driver.domain.service.RouteDomainService;
import com.school.driver.domain.vo.response.RoutePaginatedVO;
import com.school.driver.infrastructure.util.PageableResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/routes")
@RequiredArgsConstructor
@Tag(name = "Route Controller", description = "Controller to manage routes")
public class RouteController {

    private final RouteDomainService routeDomainService;
    private final RouteMapper routeMapper;

    @PostMapping
    @Operation(summary = "Iniciar rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rota iniciada com sucesso")
    })
    public IdResponseDTO startRoute(@RequestBody @Valid RouteStartRequestDTO requestDTO){
        Route routeStarted = routeDomainService.initializeRoute(routeMapper.fromRequestStartDtoToRouteDomain(requestDTO));
        return new IdResponseDTO(routeStarted.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Finalizar rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rota finalizada com sucesso")
    })
    public void finalizeRoute(@PathVariable Long id){
        routeDomainService.finalizeRoute(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rota removida com sucesso")
    })
    public void removeRoute(@PathVariable Long id){
        routeDomainService.removeRoute(id);
    }

    @PostMapping("/{id}/include/student/{studentId}")
    @Operation(summary = "Incluir aluno na rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno incluido com sucesso")
    })
    public void includeStudent(@PathVariable Long id,
                               @PathVariable Long studentId){
        routeDomainService.includeStudent(id, studentId);
    }

    @DeleteMapping("/{id}/remove/student/{studentId}")
    @Operation(summary = "Remover aluno da rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno removido da rota com sucesso")
    })
    public void removeStudent(@PathVariable Long id,
                              @PathVariable Long studentId){
        routeDomainService.removeStudent(id, studentId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rota consultada com sucesso")
    })
    public RouteResponseDTO findRoute(@PathVariable Long id){
        Route routeDetail = routeDomainService.findRoute(id);
        return routeMapper.fromRouteDomainToResponseDto(routeDetail);
    }

    @GetMapping
    @Operation(summary = "Listar rotas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rotas listadas com sucesso")
    })
    public ResponsePageableDTO<RouteListItemResponseDTO> findAllRoutes(RouteListFilterRequestDTO requestDTO){
        RoutePaginatedVO routes = routeDomainService.findRoutesByFilter(routeMapper.fromListRequestDtoToRequestVo(requestDTO));
        return new ResponsePageableDTO<>(routeMapper.fromRouteDomainListToResponseDtoList(routes.getRoutes()), PageableResponseUtil.builPageResponseDTO(requestDTO, routes.getTotalItems()));
    }
}
