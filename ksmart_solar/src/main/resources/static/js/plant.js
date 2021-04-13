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



$('#plantDelete').click(function(){
	var plName = $(this).attr('data-plName');
	var plCode = $(this).attr('data-plCode');
	console.log(plName);
	console.log(plCode);
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
					//plCode 발전소 코드로 삭제 처리 (ajax로 처리)
					$.ajax({
				        url     : '/ajax/plantDelete',
				        type    : 'POST',
				        data    : {plCode : plCode},
				        success : function(data) {
				        	console.log(data);
				        	if(data == 1){
				        		Swal.fire({
									  title: '삭제처리가 완료되었습니다.!',
									  icon: 'success'
									});
				        		setTimeout(function(){
				        			document.location.href = '/plant/plantList';
				        		}, 2000);
				        	}else{
				        		Swal.fire({
									  title: '삭제처리가 실패했습니다!',
									  text: '다시 시도해주세요.',
									  icon: 'error'
									});
				        		setTimeout(function(){
					        		document.location.href = '/plant/plantList';
				        		}, 2000);
				        	}
				        },
				        error : function(xhr,status,error) {
				        	console.log("xhr: " + xhr);
				        	console.log("status: " + status);
				        	console.log("error: " + error);
				        }
				    });
				  } else if (result.isDenied) {
				    Swal.fire('취소되었습니다.', '', 'info')
				  }
				})
		  } else if (result.isDenied) {
		    Swal.fire('취소되었습니다.', '', 'info')
		  }
		})
});


$('#plantModify').click(function(){
	var plName = $(this).attr('data-plName');
	var plCode = $(this).attr('data-plCode');
	console.log(plName);
	console.log(plCode);
	Swal.fire({
		  title: plName +' 정보를 수정 하시겠습니까?',
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
		    Swal.fire('수정화면으로 이동합니다.', '', 'info');
			$('#plCode').val(plCode);
			setTimeout(() => {
				$('#plCodeForm').submit();
			},2000);
		  } else if (result.isDenied) {
		    Swal.fire('취소되었습니다.', '', 'info');
		  }
		});
});


function kakaoMap(){
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	var plAddr = $('#plAddr').attr('data-plAddr');
	var plName = $('#plantDelete').attr('data-plName');
	geocoder.addressSearch(plAddr, function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + plName + '</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});
}

