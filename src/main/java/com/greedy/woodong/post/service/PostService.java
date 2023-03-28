package com.greedy.woodong.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.woodong.category.dto.CategoryDTO;
import com.greedy.woodong.category.entity.Category;
import com.greedy.woodong.category.repository.CategoryRepository;
import com.greedy.woodong.common.paging.SelectCriteria;
import com.greedy.woodong.member.entity.Member;
import com.greedy.woodong.member.repository.MemberRepository;
import com.greedy.woodong.post.dto.PicDTO;
import com.greedy.woodong.post.dto.PostDTO;
import com.greedy.woodong.post.dto.PostPicDTO;
import com.greedy.woodong.post.entitiy.Pic;
import com.greedy.woodong.post.entitiy.Post;
import com.greedy.woodong.post.entitiy.PostPic;
import com.greedy.woodong.post.repository.FindMaxPostRepository;
import com.greedy.woodong.post.repository.PicRepository;
import com.greedy.woodong.post.repository.PostPicRepository;
import com.greedy.woodong.post.repository.PostRepository;


@Service
public class PostService implements UserDetailsService{
   
   private final CategoryRepository categoryRepository;
   private final PostRepository postRepository;
   private final ModelMapper modelMapper;         // modelMapper 빈을 선언
   private final FindMaxPostRepository findMaxPostRepository;
   private final MemberRepository memberRepository;
   private final PostPicRepository postPicRepository;
   private final PicRepository picRepository;
   
   @Autowired
   public PostService(MemberRepository memberRepository, FindMaxPostRepository findMaxPostRepository, PostRepository postRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, PostPicRepository postPicRepository, PicRepository picRepository) {
      this.postRepository = postRepository;
      this.modelMapper = modelMapper;
      this.categoryRepository = categoryRepository;
      this.findMaxPostRepository = findMaxPostRepository;
      this.memberRepository = memberRepository;
      this.postPicRepository = postPicRepository;
      this.picRepository = picRepository;
   }
   
   @Transactional
   /* 게시물 상세 조회 */
   public PostDTO findPostByPostCode(int postCode) {

      Post post = postRepository.findById(postCode).get();
      
      return modelMapper.map(post, PostDTO.class);
   }
   
   @Transactional
   public PicDTO findPicByPostCode(int PostCode) {

      /* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
      Pic postcode = picRepository.findByPostCode(PostCode);
      
      System.out.println("postcode: " + postcode);
      /* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
      return modelMapper.map(postcode, PicDTO.class);
   }
   
   @Transactional
   /* 게시물 전체 조회 */
   public List<PostDTO> findAllPost() {

      List<Post> postList = postRepository.findAll(Sort.by("postCode"));
      
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
   }
   
   @Transactional
   /* 카테고리별(clothes) 게시물 조회*/
   public List<PostDTO> findAllCategoryClothes() {
      
      List<Post> postList = postRepository.findAll(Sort.by("postCode"));
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).filter(post->post.getCategoryCode()==1).collect(Collectors.toList());
   }
   
   
   @Transactional
   /* 카테고리별(beauty) 게시물 조회*/
   public List<PostDTO> findAllCategoryBeauty() {
      List<Post> postList = postRepository.findAll(Sort.by("postCode"));
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).filter(post->post.getCategoryCode()==4).collect(Collectors.toList());
   }
   
   @Transactional
   /* 카테고리별(culture) 게시물 조회*/
   public List<PostDTO> findAllCategoryCulture() {
      List<Post> postList = postRepository.findAll(Sort.by("postCode"));
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).filter(post->post.getCategoryCode()==3).collect(Collectors.toList());
   }
   
   @Transactional
   /* 카테고리별(it) 게시물 조회*/
   public List<PostDTO> findAllCategoryIt() {
      List<Post> postList = postRepository.findAll(Sort.by("postCode"));
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).filter(post->post.getCategoryCode()==2).collect(Collectors.toList());
   }
   
   @Transactional
   /* 카테고리별(furniture) 게시물 조회*/
   public List<PostDTO> findAllCategoryFurniture() {
      
      List<Post> postList = postRepository.findAll(Sort.by("postCode"));
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).filter(post->post.getCategoryCode()==5).collect(Collectors.toList());
   }
   
   @Transactional
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return null;
   }
   
   /* post/regist 카테고리 insert용 */
   @Transactional
   public List<CategoryDTO> findAllCategory() {

      List<Category> categoryList = categoryRepository.findAllCategory();
      
      /* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<CategoryDTO>로 반환 */
      return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
   }
   
   @Transactional
   public void registNewPost(PostDTO newPost) {

      postRepository.save(modelMapper.map(newPost, Post.class));
   }
   
   @Transactional
   public void registPicture(PostPicDTO ppd) {
      
      postPicRepository.save(modelMapper.map(ppd, PostPic.class));
   }
   
   
   @Transactional
   /* 게시물 최신 번호 */
   public int findMaxPost() {
      
      int maxPostNum = findMaxPostRepository.findMaxPost();
      
      return maxPostNum;
   }
   
   @Transactional
   /* 셀러 코드 */
   public int postSellerCode(String username) {
      Member foundMember = memberRepository.findByMemberId(username);
      int postSellerCode = foundMember.getMemberCode();
      return postSellerCode;
   }

   @Transactional
   public int selectTotalCount(String searchCondition, String searchValue) {

      int count = 0;
      if(searchValue != null) {
         if("productName".equals(searchCondition)) {
            count = postRepository.countByProductNameContaining(searchValue);
         }

//         /* 가격 검색일 경우 */
//         if("menuPrice".equals(searchCondition)) {
//            count = menuRepository.countByMenuPriceLessThanEqual(Integer.valueOf(searchValue));
//         }
      } else {
         count = (int)postRepository.count();
      }

      return count;
   }
   
   @Transactional
   public List<PostDTO> searchPostList(SelectCriteria selectCriteria) {

      int index = selectCriteria.getPageNo() - 1;         // Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
      int count = selectCriteria.getLimit();
      String searchValue = selectCriteria.getSearchValue();

      /* 페이징 처리와 정렬을 위한 객체 생성 */
      Pageable paging = PageRequest.of(index, count, Sort.by("postCode"));   // Pageable은 org.springframework.data.domain패키지로 import

      List<Post> postList = new ArrayList<Post>();
      if(searchValue != null) {

         /* productName 검색일 경우 */
         if("productName".equals(selectCriteria.getSearchCondition())) {
            postList = postRepository.findByProductNameContaining(selectCriteria.getSearchValue(), paging);
         }

      } else {
         postList = postRepository.findAll(paging).toList();
      }

      /* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
   }

   @Transactional
   public void modifyPost(PostDTO post) {

      Post foundPost = postRepository.findById((Integer)post.getPostCode()).get();

      foundPost.setProductName(post.getProductName());
      foundPost.setPrice(post.getPrice());
      foundPost.setOrderableYn(post.getOrderableYn());
      foundPost.setTradeableYn(post.getTradeableYn());
      foundPost.setProductContent(post.getProductContent());
      foundPost.setProductTotal(post.getProductTotal());
      foundPost.setLocation(post.getLocation());
      foundPost.setCategoryCode(post.getCategoryCode());
      foundPost.setDiscountYn(post.getDiscountYn());
      foundPost.setOrderableYn(post.getOrderableYn());
      
   }

//    @Transactional
//    public void deletePost(int postCode) {
//        postRepository.deleteById(postCode);
//    }

    /*진이거.. list용*/
   public PostDTO tradePostCode(int memberCode) {
      Post foundPostCode = postRepository.findByPostCode(memberCode);
      return null;
   }

   /*병합충돌중 생성됨 필요없으면 지울 것*/
    @Transactional
   public List<PostDTO> findByMemberCode(int memberCode) {
      List<Post> postList = postRepository.findBySellerCode(memberCode);
      
      return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
   }


   @Transactional
   public void delete(int postCode) {
      postRepository.deleteById(postCode);
   }

   @Transactional
   public PostDTO orderableNByPostCode(int postCode) {
      Post foundPost = postRepository.findByPostCode(postCode);
      foundPost.setOrderableYn("N");
       return modelMapper.map(foundPost, PostDTO.class);
      }
   
   @Transactional
   public PostDTO orderableYByPostCode(int postCode) {
      Post foundPost = postRepository.findByPostCode(postCode);
      foundPost.setOrderableYn("Y");
       return modelMapper.map(foundPost, PostDTO.class);
      }
}
