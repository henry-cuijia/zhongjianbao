package zhongjianbaoapi.com.zhongjianbaoapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 班组
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FaTeam implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班组名称
     */
    private String name;

    /**
     * 所在城市
     */
    private Integer city;

    /**
     * 所在项目
     */
    private Integer xiangmu;

    /**
     * 创建者
     */
    private Integer uid;


}
