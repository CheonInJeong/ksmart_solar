$('#plantDelete').click(function(){
			var plName = $(this).attr('data-plName');
			var plCode = $(this).attr('data-plCode');
			Swal.fire({
				  title: plName +' 정보를 삭제 하시겠습니까?',
				  showDenyButton: true,
				  confirmButtonText: `Yes`,
				  denyButtonText: `No`,
				  customClass: {
				    cancelButton: 'order-1 right-gap',
				    confirmButton: 'order-2',
				    denyButton: 'order-3',
				  }
				}).then((result) => {
				  if (result.isConfirmed) {
					  Swal.fire({
						  title: '정말 삭제 하시겠습니까?',
						  showDenyButton: true,
						  confirmButtonText: `Yes`,
						  denyButtonText: `No`,
						  customClass: {
						    cancelButton: 'order-1 right-gap',
						    confirmButton: 'order-2',
						    denyButton: 'order-3',
						  }
						}).then((result) => {
						  if (result.isConfirmed) {
							//plCode 발전소 코드로 삭제 처리
							
						    Swal.fire('삭제처리 되었습니다!', '', 'success')
						  } else if (result.isDenied) {
						    Swal.fire('취소되었습니다.', '', 'info')
						  }
						})
				  } else if (result.isDenied) {
				    Swal.fire('취소되었습니다.', '', 'info')
				  }
				})
		});



$('.benefitAnalysis').click(function(){
	document.location.href = '/plant/plantDetail/benefitAnalysis';
	
});
$('.generationAnalysis').click(function(){
	document.location.href = '/plant/plantDetail/generationAnalysis';
	
});
$('.generationPredict').click(function(){
	document.location.href = '/plant/plantDetail/generationPredict';
	
});
$('#addPlant').click(function(){
	document.location.href = '/plant/addPlant';
});
