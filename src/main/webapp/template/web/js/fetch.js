		let resultList = document.getElementById('resultList');
		let suggestSearchNewsList = document.getElementById('suggestSearchNewsList');
		let searchBar = document.getElementById('searchBar');

	document.addEventListener('readystatechange',event => {
	if(event.target.readyState === 'interactive'){
		resultList.style.display = 'none';
	}else if (event.target.readyState === 'complete'){
	
		let results = [];
		let filterResults = [] ;

		

		let trimStr = (str) => {
			return str.replace(/^\s+|\s+$/ ,'') ;
		}


		searchBar.addEventListener('keyup', (e)=> {
			let searchString = e.target.value.toLowerCase() ;
			if(trimStr(searchString).length < 1){
				resultList.style.display = 'none' ;
			}else {
				loadAllNews();
				filterResults = results.filter((result)=>{
					return (
						result.title.toLowerCase().includes(searchString) ||
						result.shortDescription.toLowerCase().includes(searchString)
					);

				});
					
					displayResults(filterResults);
					resultList.style.display = 'block';
			}
		});	
		
		let loadAllNews = async() => {
			try {
			let res = await fetch('http://localhost:8080/api/news',{
		    method : 'GET',
		    headers : {
		      'Content-Type': 'application/json'
		      }
		    });

				let news = await res.json();
				results = news.listResult;
			}catch(err){
				console.log(err);
			}
		}

		let displayResults = (results) => {
			let htmlString = results.map((result)=> {
				let newsDatePosted = moment(result.timeNewsPosted).fromNow();
				let itemUrl = "/bai-viet?title=" + result.title + "&page=1"+"&limit=3" ;
				let itemImageUrl = "/image-response/" + result.thumbnail ;
				return `<li class="list-group-item"><a href="${itemUrl}" class="list-group-item list-group-item-action flex-column align-items-start"><div class="d-flex w-100 justify-content-between"><h5 class="mb-1" style="white-space:nowrap;width:80%;overflow:hidden; text-overflow:ellipsis">${result.title}</h5><small>${newsDatePosted}</small></div><p class="mb-1" style="white-space:nowrap;width:80%;overflow:hidden; text-overflow:ellipsis" >${result.shortDescription}</p><img src="${itemImageUrl}" width="40%" /></a></li>`
			}).join('');

			suggestSearchNewsList.innerHTML = htmlString; 
		}
	}
 });

