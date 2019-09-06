package com.footstamp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.footstamp.bean.AlarmBean;
import com.footstamp.bean.ReviewBean;
import com.footstamp.controller.DynamicIdBinder;

@Service
public class ReviewService {

	private Map<String, HashMap<String, ReviewBean>> reviews;

	public ReviewService() {
		this.reviews = Collections
				.synchronizedMap(new HashMap<String, HashMap<String, ReviewBean>>());
	}

	public Map<String, HashMap<String, ReviewBean>> getReviews() {
		return reviews;
	}

	public void setReviews(HashMap<String, HashMap<String, ReviewBean>> reviews) {
		this.reviews = reviews;
	}

	public int addReview(String bulletinId, ReviewBean bean) {
		String firstKey = null;
		String secondKey = null;
		Iterator<String> iterator = this.reviews.keySet().iterator();
		Iterator<String> iterator2 = null;
		// 키값 중복 체크
		while (iterator.hasNext()) {
			firstKey = iterator.next();
			if (firstKey.equals(bulletinId))
				return 0;
			iterator2 = this.reviews.get(firstKey).keySet().iterator();
			while (iterator2.hasNext()) {
				secondKey = iterator2.next();
				if (secondKey.equals(bean.getReviewId()))
					return 0;
			}
		}
		HashMap<String, ReviewBean> innerMap = new HashMap<String, ReviewBean>();
		innerMap.put(bean.getReviewId(), bean);
		this.reviews.put(bulletinId, innerMap);
		// reviewAlarm(bulletinId, bean);//데이터 넣고 해야함
		return 1;
	}

	public List<ReviewBean> searchByWriterId(String writerId) {
		Iterator<String> outerIter = this.reviews.keySet().iterator();
		Iterator<String> innerIter = null;
		ReviewBean bean = null;
		HashMap<String, ReviewBean> innerMap = null;
		List<ReviewBean> res = null;
		while (outerIter.hasNext()) {
			innerMap = this.reviews.get(outerIter.next());
			innerIter = innerMap.keySet().iterator();
			while (innerIter.hasNext()) {
				bean = innerMap.get(innerIter.next());
				if (bean.getWriterId().equals(writerId)) {
					if (res == null) {
						res = new ArrayList<ReviewBean>();
					}
					res.add(bean);
				}
			}
		}
		return res;
	}

	/**
	 * res[0] = bean.getWriterId(); res[1] = bean.getReviewId(); res[2] =
	 * bean.getBulletinId(); res[3] = bean.getIsLike(); res[4] =
	 * bean.getIsStory(); res[5] = bean.getDate(); res[6] = bean.getContent();
	 */
	public String[] searchByReviewId(String reviewId) {
		String[] res = null;
		Iterator<String> outerIter = this.reviews.keySet().iterator();
		ReviewBean bean = null;
		while (outerIter.hasNext()) {
			bean = this.reviews.get((String) outerIter.next()).get(reviewId);
			if (bean != null) {
				res = new String[7];
				res[0] = bean.getWriterId();
				res[1] = bean.getReviewId();
				res[2] = bean.getBulletinId();
				res[3] = bean.getIsLike();
				res[4] = bean.getIsStory();
				res[5] = bean.getDate();
				res[6] = bean.getContent();
				return res;
			}
		}
		return res;
	}

	public String[][] searchByBulletinId(String bulletinId) {
		String[][] res = null;
		Iterator<String> innerIter = null;
		HashMap<String, ReviewBean> innerMap = null;
		int i = 0;
		innerMap = this.reviews.get(bulletinId);
		if (innerMap != null) {
			innerIter = innerMap.keySet().iterator();
			List<ReviewBean> sortList = new ArrayList<ReviewBean>();
			while (innerIter.hasNext()) {
				sortList.add(innerMap.get(innerIter.next()));
			}
			// 날짜순으로 정렬
			Collections.sort(sortList, new Comparator<ReviewBean>() {
				public int compare(ReviewBean obj1, ReviewBean obj2) {
					return obj1.getDate().compareToIgnoreCase(obj2.getDate());
				}
			});
			res = new String[innerMap.size()][7];
			for (ReviewBean bean : sortList) {
				res[i][0] = bean.getWriterId();
				res[i][1] = bean.getReviewId();
				res[i][2] = bean.getBulletinId();
				res[i][3] = bean.getIsLike();
				res[i][4] = bean.getIsStory();
				res[i][5] = bean.getDate();
				res[i][6] = bean.getContent();
				i++;
			}
		}
		return res;
	}

	public int deleteByReviewId(String bulletinId, String reviewId) {
		int res = 0;
		HashMap<String, ReviewBean> innerMap = this.reviews.get(bulletinId);
		ReviewBean delRes = null;
		if (innerMap != null) {
			delRes = innerMap.remove(reviewId);
			if (delRes != null) {// 잘 삭제 됐다면
				res = 1;
			}
		}
		return res;
	}

	public int deleteAllByBulletinId(String bulletinId) {
		int res = 0;
		HashMap<String, ReviewBean> delRes = this.reviews.remove(bulletinId);
		if (delRes != null)
			res = 1;
		return res;
	}

	public int deleteByWriterId(String writerId) {
		int res = 0;
		Iterator<String> outerIter = this.reviews.keySet().iterator();
		Iterator<String> innerIter = null;
		HashMap<String, ReviewBean> innerMap = null;
		ReviewBean bean = null;
		while (outerIter.hasNext()) {
			innerMap = this.reviews.get(outerIter.next());
			innerIter = innerMap.keySet().iterator();
			while (innerIter.hasNext()) {
				bean = innerMap.get(innerIter.next());
				if (bean.getWriterId().equals(writerId)) {
					innerMap.remove(bean.getReviewId());
					res = 1;
				}
			}
		}
		return res;

	}

	public String[] modifyComment(String content, String reviewId) {
		String[] res = null;
		Iterator<String> outerIter = this.reviews.keySet().iterator();
		ReviewBean bean = null;
		while (outerIter.hasNext()) {
			bean = this.reviews.get(outerIter.next()).get(reviewId);
			if (bean != null) {
				bean.setContent(content);
				res = new String[7];
				res[0] = bean.getWriterId();
				res[1] = bean.getReviewId();
				res[2] = bean.getBulletinId();
				res[3] = bean.getIsLike();
				res[4] = bean.getIsStory();
				res[5] = bean.getDate();
				res[6] = bean.getContent();
			}
		}
		return res;
	}

	public String getReviewCount(String reviewType, String bulletinId) {
		String res = null;
		int count = 0;
		HashMap<String, ReviewBean> innerMap = this.reviews.get(bulletinId);
		Iterator<String> innerIter = innerMap.keySet().iterator();
		while (innerIter.hasNext()) {
			if (innerMap.get(innerIter.next()).getIsLike().equals(reviewType))
				count++;
		}
		res = "" + count;
		return res;
	}

	/**
	 * String[0] 좋아요 수 String[1] 댓글 수
	 */
	public String[] getReviewsCount(String bulletinId) {
		String[] res = new String[2];
		res[0] = getReviewCount("true", bulletinId);// 좋아요
		res[1] = getReviewCount("false", bulletinId);// 댓글
		return res;
	}

	public List<String> getWriterList(String bulletinId) {
		List<String> res = null;
		HashMap<String, ReviewBean> innerMap = this.reviews.get(bulletinId);
		Iterator<String> innerIter = innerMap.keySet().iterator();
		while (innerIter.hasNext()) {
			if (res == null)
				res = new ArrayList<String>();
			res.add(innerMap.get(innerIter.next()).getWriterId());
		}
		return res;
	}

	public int reviewAlarm(String bulletinId, ReviewBean bean) {
		String[] frags = DynamicIdBinder.bulletinIdDisassemble(bulletinId);
		/*return AlarmService.getInstance().sendAlarm(
				bulletinId,
				new AlarmBean(bean.getIsLike(), "review", bean.getWriterId(),
						frags[0], bean.getDate(), "false"));*/
		return 1;
	}

	public void loadReviewsService(String id) {

	}

	public void load() {

	}

	public void save() {

	}

	public void dataTransfer() {

	}

	public void updateData() {

	}

	@Override
	public String toString() {
		return "ReviewService [reviews=" + reviews + "]";
	}
}
