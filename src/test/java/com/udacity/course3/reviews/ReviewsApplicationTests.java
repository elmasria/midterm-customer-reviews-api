package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ProductsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = "spring.datasource.url=")
public class ReviewsApplicationTests {

	@Autowired
	private ProductsRepository productRepository;

	@Autowired
	private ReviewsRepository reviewRepository;

	@Autowired
	private CommentsRepository commentRepository;

	private Product addTestProduct() {
		Product product = new Product();
		product.setName("New Test Product");
		product.setDescription("Product Description");
		System.out.println(product.getDescription());
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	};
	private Review addTestReview(Product product) {
		Review review = new Review();
		review.setProduct(product);
		review.setTitle("Review Title");
		review.setOwnerName("Ahmad");
		review.setReview("this is my review");
		// save review
		Review newReview = reviewRepository.save(review);
		return newReview;
	};

	private  Comment addTestComment(Review review) {
		Comment comment =  new Comment();
		comment.setReview(review);
		comment.setComment("my comments");
		comment.setOwnerName("Ahmad");
		Comment savedComment = commentRepository.save(comment);
		return savedComment;
	}

	@Test
	public void contextLoads() {
		assertThat(productRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(commentRepository).isNotNull();
	}

	@Test
	public void addNewProduct() throws Exception {
		Product savedProduct = addTestProduct();
		assertThat(savedProduct.getId()).isGreaterThan(0);
		assertThat(savedProduct.getName()).isEqualTo("New Test Product");
		assertThat(savedProduct.getDescription()).isEqualTo("Product Description");
	}

	@Test
	public void getProductByID() throws Exception {
		// add product first
		Product savedProduct = addTestProduct();

		// check get
		Optional<Product> productResult = productRepository.findById(savedProduct.getId());
		assertThat(productResult.isPresent()).isTrue();
		assertThat(productResult.get().getId()).isEqualTo(savedProduct.getId());
	}

	@Test
	public void addNewReview() throws Exception {
		Product savedProduct = addTestProduct();
		Review newReview = addTestReview(savedProduct);

		assertThat(newReview.getId()).isGreaterThan(0);
		assertThat(newReview.getTitle()).isEqualTo("Review Title");
		assertThat(newReview.getReview()).isEqualTo("this is my review");
		assertThat(newReview.getOwnerName()).isEqualTo("Ahmad");
	}

	@Test
	public void getListReview() throws Exception {
		Product savedProduct = addTestProduct();
		addTestReview(savedProduct);
		List<Review> reviews = reviewRepository.findAllByProduct_id(savedProduct.getId());
		assertThat(reviews.size()).isGreaterThan(0);
	}

	@Test
	public void addNewComment() throws Exception {
		Product savedProduct = addTestProduct();
		Review newReview = addTestReview(savedProduct);
		Comment savedComment = addTestComment(newReview);

		assertThat(savedComment.getId()).isGreaterThan(0);
		assertThat(savedComment.getComment()).isEqualTo("my comments");
		assertThat(savedComment.getOwnerName()).isEqualTo("Ahmad");
	}

	@Test
	public void getComments() throws Exception {
		Product savedProduct = addTestProduct();
		Review testReview = addTestReview(savedProduct);
		addTestComment(testReview);

		List<Comment> comments = commentRepository.findCommentByReview_id(testReview.getId());
		assertThat(comments.size()).isGreaterThan(0);
	}

}