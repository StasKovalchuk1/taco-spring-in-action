package tacos.data;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tacos.Ingredient;
import tacos.IngredientRef;
import tacos.Taco;
import tacos.TacoOrder;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@Repository
//public class JdbcOrderRepository implements OrderRepository{
//
//    private JdbcOperations jdbcOperations;
//
//    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
//        this.jdbcOperations = jdbcOperations;
//    }
//
//    @Override
//    @Transactional
//    public TacoOrder save(TacoOrder order) {
//        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("" +
//                "insert into Taco_Order " +
//                "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) " +
//                "values (?,?,?,?,?,?,?,?,?)", Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
//        pscf.setReturnGeneratedKeys(true);
//
//        order.setPlacedAt(new Date());
//        PreparedStatementCreator psc =
//                pscf.newPreparedStatementCreator(
//                        Arrays.asList(
//                                order.getDeliveryName(),
//                                order.getDeliveryStreet(),
//                                order.getDeliveryCity(),
//                                order.getDeliveryState(),
//                                order.getDeliveryZip(),
//                                order.getCcNumber(),
//                                order.getCcExpiration(),
//                                order.getCcCVV(),
//                                order.getPlacedAt()));
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        int orderId = (int) keyHolder.getKeys().get("id");
//        order.setId(orderId);
//
//        List<Taco> tacos = order.getTacos();
//        for (Taco taco : tacos) {
//            saveTaco(orderId, taco);
//        }
//        return order;
//    }
//
//    private int saveTaco(int orderId, Taco taco) {
//        taco.setCreatedAt(new Date());
//        PreparedStatementCreatorFactory pscf =
//                new PreparedStatementCreatorFactory(
//                        "insert into Taco (name, taco_order, created_at) values (?, ?, ?)",
//                        Types.VARCHAR, Type.LONG, Types.TIMESTAMP
//                );
//        pscf.setReturnGeneratedKeys(true);
//        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
//                Arrays.asList(
//                        taco.getName(),
//                        orderId,
//                        taco.getCreatedAt()));
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        int tacoId = (int) keyHolder.getKeys().get("id");
//        taco.setId(tacoId);
//        saveIngredientRefs(tacoId, taco.getIngredients());
//        return tacoId;
//    }
//
//    private void saveIngredientRefs(int tacoId, List<Ingredient> ingredients) {
//        for (Ingredient ingredient : ingredients) {
//            jdbcOperations.update(
//                    "insert into Ingredient_Ref (ingredient, taco) values (?, ?)",
//                    ingredient.getId(), tacoId);
//        }
//    }
//}
