package com.example.controller;

import com.example.model.OrdemServico;
import com.example.model.Resolvedor;
import com.example.repository.OSRepository;
import com.example.repository.ResolvedorRepository;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping(path = "/")
@Api(value = "ResolvedorControllerApi", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResolvedorController {

    @Autowired
    private ResolvedorRepository resolvedorRepository;
    
    @Autowired
    private OSRepository oSRepository;

    //Adiciona 1 usuário 
    @GetMapping(path = "/add")
    @CrossOrigin
    public @ResponseBody
    String addNewUser(@RequestParam String name) {
        Resolvedor n = new Resolvedor();
        n.setNome(name);
        resolvedorRepository.save(n);
        //TODO: DEFINIR A PÁGINA PARA QUAL DESEJA FAZER 
        return "<a href=#> voltar </a>";
    }

    //Lista todos os utuários do banco
    @GetMapping(path = "/list")
    @CrossOrigin
    public @ResponseBody
    Iterable<Resolvedor> listar() {
        return resolvedorRepository.findAll();
    }

    //Obtem a lista com a sequencia de resoulução dos problemas 
    @GetMapping(path = "/im-day", produces = "application/json")
    @ApiOperation("Retorna lista com a ordem de resolução dos incidentes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = OrdemServico.class)})
    @CrossOrigin
    public @ResponseBody()
    String imDay() throws JSONException {
        Calendar calendar = null;
        OrdemServico os = null;
        ArrayList<Resolvedor> arrResolvedores = null;
        Resolvedor resolvedor = null;
        String descricaoDia = "";
        int limiteArray = 0;
        int iContAux = 0;

        //Instancia os objetos 
        calendar = Calendar.getInstance();
        arrResolvedores = new ArrayList<>();

        oSRepository.deleteAll();
        
        //Adiciona os resolvedores cadastrados no arrayList 
        resolvedorRepository.findAll().forEach(arrResolvedores::add);
        
        //Armazena o limite do arrayList
        limiteArray = arrResolvedores.size() - 1;

        //Subtrai um dia da data atual 
        calendar.add(Calendar.DAY_OF_WEEK, -1);

        //Realiza loop equivalente ao período para qual se deseja obter os dados 
        for (int x = 1; x <= 11; x++) {
            //Define a descrição do dia 
            if (x == 1) {
                descricaoDia = "(Ontem)";
            } else if (x == 2) {
                descricaoDia = "(Hoje)";
            } else if (x == 3) {
                descricaoDia = "(Amanha)";
            } else {
                descricaoDia = "";
            }

            //Se for final de semana não aloca o funcionario 
            if (!Util.isWeekend(calendar)) {
                resolvedor = arrResolvedores.get(iContAux);

                if (iContAux == limiteArray) {
                    iContAux = 0;
                } else {
                    iContAux++;
                }
            }

            //Instancia o objeto 
            os = new OrdemServico();

            os.setData(Util.parseData(calendar, "dd/MM/yyyy"));
            os.setDiaSemana(Util.getDayOfWeek(calendar));
            os.setDiaDescricao(descricaoDia);
            os.setResolvedor(resolvedor);

            oSRepository.save(os);
            
            //Soma 1 dia na data 
            calendar.add(Calendar.DAY_OF_WEEK, 1);

            //Limpa o objeto que será utilizado posteriormente 
            resolvedor = null;
        }
            
        JSONArray jSONArray = new JSONArray(new Gson().toJson(oSRepository.findAll()));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("lista", jSONArray);
        
        return jSONObject.toString();
    }
}
