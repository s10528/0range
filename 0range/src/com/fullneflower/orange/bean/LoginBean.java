package com.fullneflower.orange.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.StaffDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.InputChecker;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.vo.StaffVO;

/**
 * [説明]ログイン認証処理を行うクラス
 * @author Kawamori Wada tanaka Sanpei Nakajima
 *
 */
public class LoginBean implements IFBean{

	/**
	 * [機能]ログイン認証処理を行うメソッド
	 * [説明]入力されたID,PWを用いDBと照合を行う
	 * [処理結果]一致:セッションを与えメイン画面に遷移
	 *         不一致:ログイン画面に遷移
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws OrangeException {
		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("backLogin.failure");
		//入力された値の受け取り
		String staffId = request.getParameter("staffId");
		String password = request.getParameter("password");
		// 入力チェック(空文字ID)
		if (InputChecker.isEmpty(staffId)) {
			System.out.println("空文字チェックID NG!");
			String msg = ResourceProperty.getMessage("backLogin.error.null");
			request.setAttribute("errMsg", msg);
			return url;
		}
		// 入力チェック(空文字password)
		if (InputChecker.isEmpty(password)) {
			System.out.println("空文字チェックpassword NG!");
			String msg = ResourceProperty.getMessage("backLogin.error.null");
			request.setAttribute("errMsg", msg);
			return url;
		}
		// 入力チェック(半角数字ID 完了後1000以上判断)
		if (!InputChecker.isNum(staffId)) {
			System.out.println("半角数字チェックID NG!");
			String msg = ResourceProperty.getMessage("backLogin.error.authenticate");
			request.setAttribute("errMsg", msg);
		}else if(!InputChecker.idUnderThousand(staffId)){
			System.out.println("1000以上チェックID NG!");
			String msg = ResourceProperty.getMessage("backLogin.error.authenticate");
			request.setAttribute("errMsg", msg);
			return url;
		}
		// 入力チェック(半角英数字password)
		if (!InputChecker.isAlphaNumeric(password)) {
			System.out.println("半角英数字チェックpassword NG!");
			String msg = ResourceProperty.getMessage("backLogin.error.authenticate");
			request.setAttribute("errMsg", msg);
			return url;
		}
		// 入力チェック(桁数staffId)
		if (!InputChecker.idStaffLength(staffId)) {
			System.out.println("桁数チェックstaffId NG!");
			String msg = ResourceProperty.getMessage("backLogin.error.authenticate");
			request.setAttribute("errMsg", msg);
			return url;
		}
		// StaffVOをインスタンス生成
		StaffVO staffVO = new StaffVO();
		// staffVOに値を詰める
		staffVO.setStaffId(staffId);
		staffVO.setPassword(password);
		// connectionを取得
		Connection connection = cm.getConnection();
		try{
			//StaffDAOをインスタンス生成
			StaffDAO staffDAO = new StaffDAO(connection);
			// StaffDAOの呼び出し
			int check = staffDAO.staffCheck(staffVO);
			// 遷移先URLの決定
			if (check == 1) {
				url=ResourceProperty.getUrl("backLogin.success");
				// セッション開始
				HttpSession session = request.getSession(true);
				Object checkObject = check;
				session.setAttribute("check", checkObject);
			}else{
				System.out.println("残念！ログイン認証失敗");
				String msg = ResourceProperty.getMessage("backLogin.error.authenticate");
				request.setAttribute("errMsg", msg);
				return url;
			}
		} catch(Exception e) {
			throw new OrangeException(e);
		} finally {
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}